package ece651.action;

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

	public void setSession(Map<String, Object> session) {
		this.session = session;	
	}
	
	public void setRequest(Map<String, Object> request) {
		this.request = request;
		
	}

	public String CreatePatient(){	
		System.out.println("CreatePatient is executed");//used for debug
		PatientDao patientDao = new PatientDaoImpl(); //initiate PatientDao instance
		request.put("Operation", "Create New Patient:"+patient.getPatientName());
		//if failed return String "fail"
		try{
			patientDao.savePatient(patient);
		}catch (Exception e){
			request.put("ReasonOfFailure", e.getMessage());
			return ERROR;
		}		
		//if success return String "success"
		return SUCCESS;		
	}
	
	public String ViewPatient(){
		System.out.println("ViewPatient is executed");
		PatientDao patientDao = new PatientDaoImpl();
		Patient retrievePatient;
		try{
			//TODO the parameter for "searchPatient should be patient's healthcardID"
			retrievePatient = patientDao.searchPatient(healthCardID);
			 if (retrievePatient == null){
				request.put("ReasonOfFailure", "Patient with Health Card ID"+ healthCardID +"Does not Exist");
			  return ERROR;
			  }//end of if
			} catch (Exception e){
				request.put("ReasonOfFailure", e.getMessage());
				return ERROR;
			}//end of catch
			request.put("RetrievedPatient", retrievePatient);
			return SUCCESS;
		}//end of ViewPatient
	
	public String EditPatient(){
		System.out.println("EditPatient is executed");
		PatientDao patientDao = new PatientDaoImpl();
		request.put("Operation", "Edit Patient Info:"+patient.getPatientName());
		try{
			patientDao.updatePatient(patient);
		}catch (Exception e){
			request.put("ReasonOfFailure", e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
}

	

