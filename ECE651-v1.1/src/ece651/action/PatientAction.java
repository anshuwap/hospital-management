package ece651.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import ece651.dao.*;
import ece651.model.*;

public class PatientAction extends ActionSupport implements SessionAware, RequestAware{

	private Map<String, Object> session;
	private Map<String, Object> request;
	private Patient patient;
	private String healthCardID;
	private Patient retrievePatient;
	private String patientBirthday;
	private String operationStatus;
	private ArrayList<Visitation> patientVisitation;
	
	private String visitationQuery;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getHealthCardID() {
		return healthCardID;
	}

	public void setHealthCardID(String healthCardID) {
		this.healthCardID = healthCardID;
	}

	public Patient getRetrievePatient() {
		return retrievePatient;
	}

	public void setRetrievePatient(Patient retrievePatient) {
		this.retrievePatient = retrievePatient;
	}

	public String getPatientBirthday() {
		return patientBirthday;
	}

	public void setPatientBirthday(String patientBirthday) {
		this.patientBirthday = patientBirthday;
	}

	public String getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}

	public ArrayList<Visitation> getPatientVisitation() {
		return patientVisitation;
	}

	public String getVisitationQuery() {
		return visitationQuery;
	}

	public void setVisitationQuery(String visitationQuery) {
		this.visitationQuery = visitationQuery;
	}

	public void setPatientVisitation(ArrayList<Visitation> patientVisitation) {
		this.patientVisitation = patientVisitation;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;	
	}
	
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
		
	}

	public String CreatePatient(){	
		System.out.println("CreatePatient is executed");//used for debug
		PatientDao patientDao = new PatientDaoImpl(); //initiate PatientDao instance
		request.put("Operation", "Create New Patient:"+patient.getPatientName());
		
		//if failed return String "fail"
		if((!patient.getPatientName().trim().isEmpty())&&(!patient.getHealthCardId().trim().isEmpty())){
		  try{
			  Date birthday =Date.valueOf(patientBirthday.substring(0, 10));
				patient.setBirthday(birthday);
				if(patient.getGender().equalsIgnoreCase("0"))
					patient.setGender("F");
				else
					patient.setGender("M");
				patient.setHealthCardId(patient.getHealthCardId().toLowerCase());

				SystemUser user = (SystemUser) session.get("CurrentUser");
				if (null!=user){
					patient.setLastUpdtUser(user.getUsername());
				}
				patient.setVersion(1);
				
			  patientDao.savePatient(patient);
		  }catch (Exception e){
			//String errorInfo =  + e.getCause().toString();
		  this.setOperationStatus("Create New Patient Failed: Exception Happened: "+e.getMessage());
			patient = null;
			patientBirthday = null;
			return ERROR;
		  }	
		}
		else{
			this.setOperationStatus("Create New Patient Failed: Patient Name or Patient HealthcardID is null");
		    patient = null;
		    patientBirthday = null;
			return ERROR;
		}
		//if success return String "success"
		this.setOperationStatus("Create New Patient Succeeded!");
		session.put("CurrentPatient", patient);
		patient = null;
		patientBirthday = null;
		return SUCCESS;		
	}
	
	
	@SuppressWarnings("unchecked")
	public String SearchPatient(){
		System.out.println("ViewPatient is executed");
		Object searchResult = QueryPatient(healthCardID.toLowerCase());
		if(searchResult instanceof String){
		    this.setOperationStatus("Search Patient: Failed, Reason: "+(String)searchResult);
		    healthCardID = null;
		    return ERROR;
		}
		else{	
			retrievePatient = (Patient)searchResult;
			
			Object searchVisitation = QueryVisitation(retrievePatient);
			if(searchVisitation instanceof String){
				this.setVisitationQuery("Visitation Not Found, Reason: "+(String)searchVisitation);
			}
			else {
				this.setPatientVisitation((ArrayList<Visitation>)searchVisitation);
				this.session.put("PatientVisitationList", patientVisitation);
			}
			this.session.put("RetrievePatient", retrievePatient);
			this.setPatientBirthday(retrievePatient.getBirthday().toString().substring(0, 10));
			if(retrievePatient.getGender().equalsIgnoreCase("M"))
				retrievePatient.setGender("1");
			else
				retrievePatient.setGender("0");
			this.setOperationStatus("Search Patient: Found.");
			healthCardID = null;
			session.put("CurrentPatient", retrievePatient); 
			return SUCCESS;
		}
			
			//request.put("RetrievedPatient", retrievePatient);
			
		}//end of ViewPatient
	
	public String EditPatient(){
		System.out.println("EditPatient is executed");
		PatientDao patientDao = new PatientDaoImpl();
		
		try{
			Date birthday =Date.valueOf(patientBirthday);
			retrievePatient.setBirthday(birthday);
			if(retrievePatient.getGender().equalsIgnoreCase("1"))
				retrievePatient.setGender("M");
			else
				retrievePatient.setGender("F");
			retrievePatient.setHealthCardId(retrievePatient.getHealthCardId().toLowerCase());
			
			SystemUser user = (SystemUser) session.get("CurrentUser");
			if (null!=user){
				retrievePatient.setLastUpdtUser(user.getUsername());
			}
			
			patientDao.updatePatient(retrievePatient);
		}catch (Exception e){
			//request.put("OperationStatus","Update Patient: Exception Happened" +e.getMessage());
			this.setOperationStatus("Update Patient Info Failed. Exception Happened: "+e.getMessage());
			retrievePatient = null;
			patientBirthday = null;
			return ERROR;
		}
		session.put("CurrentPatient", retrievePatient); 
		retrievePatient = null;
		patientBirthday = null;
		this.setOperationStatus("Edit Patient Info Succeeded!");
		
		return SUCCESS;
	}
	
	private Object QueryPatient(String healthcard){
		Patient resultPatient = null;	
		try{
			PatientDao patientDao = new PatientDaoImpl();
			resultPatient = patientDao.searchPatient(healthcard);
			 if (resultPatient == null){
				 //request.put("OperationStatus","Search Patient: Patient Not Exist");
				 return "Patient Not Found";
			  }//end of if
			} catch (Exception e){
				//request.put("OperationStatus","Search Patient: Exception Happened" +e.getMessage());
				return "Exception Happened"+e.getMessage();
			}//end of catch
		
		return resultPatient;
	}
	
	
	private Object QueryVisitation(Patient patient){
		ArrayList<Visitation> resultVisitationList;
		try{
			VisitationDao visitationDao = new VisitationDaoImpl();
			resultVisitationList = (ArrayList<Visitation>) visitationDao.searchVisitListBypId(patient.getPatientId());
			if (resultVisitationList.isEmpty()){
				return "Visitation Not Found";
			}
		} catch(Exception e){
			return "Exception Happened"+e.getMessage();		
		}		
		return resultVisitationList;
	}
}

	

