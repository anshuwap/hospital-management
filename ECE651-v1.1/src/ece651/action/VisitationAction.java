package ece651.action;

import java.util.*;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import ece651.dao.*;
import ece651.model.*;

public class VisitationAction extends ActionSupport implements SessionAware, RequestAware{

	private Map<String, Object> session;
	private Map<String, Object> request;
	private Visitation visitation;
	private Patient retrievePatient;
	private String operationStatus;
	private SystemUser doctor;
	private int visitationId;
	private Set<DiagnosisTest> diagnosisTestList;
	private String queryDiagnosisTestList;
	private int appointmentId;

	
	
	public Visitation getVisitation() {
		return visitation;
	}


	public void setVisitation(Visitation visitation) {
		this.visitation = visitation;
	}


	public Patient getRetrievePatient() {
		return retrievePatient;
	}


	public void setRetrievePatient(Patient retrievePatient) {
		this.retrievePatient = retrievePatient;
	}


	public String getOperationStatus() {
		return operationStatus;
	}


	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}


	public SystemUser getDoctor() {
		return doctor;
	}


	public void setDoctor(SystemUser doctor) {
		this.doctor = doctor;
	}


	public int getVisitationId() {
		return visitationId;
	}


	public void setVisitationId(int visitationId) {
		this.visitationId = visitationId;
	}




	public Set<DiagnosisTest> getDiagnosisTestList() {
		return diagnosisTestList;
	}


	public void setDiagnosisTestList(Set<DiagnosisTest> diagnosisTestList) {
		this.diagnosisTestList = diagnosisTestList;
	}


	public String getQueryDiagnosisTestList() {
		return queryDiagnosisTestList;
	}


	public void setQueryDiagnosisTestList(String queryDiagnosisTestList) {
		this.queryDiagnosisTestList = queryDiagnosisTestList;
	}


	public int getAppointmentId() {
		return appointmentId;
	}


	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}


	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
		
	}


	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;	
		
	}

	//TODO create a visitation entry
	public String CreateVisitation(){
		System.out.println("CreateVisitation is executed");//used for debug
		VisitationDao visitationDao = new VisitationDaoImpl();
		Visitation newVisitation = new Visitation();	
		try{
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			newVisitation.setVisitationDate(sqlDate);
			newVisitation.setPatient((Patient)session.get("RetrievePatient"));
			newVisitation.setDoctor((SystemUser) session.get("CurrentUser"));
			newVisitation.setVisitationType("N");		
			visitationDao.saveVisitation(newVisitation);
			
		}catch(Exception e){
			this.setOperationStatus("Create New Visitation Failed! Exception Happened: "+e.getMessage());
			visitationDao.cleanup();
			return ERROR;		
		}
		finally{
			visitationDao.cleanup();
		}
			
		this.setVisitation(newVisitation);
		this.session.put("CurrentVisitation", visitation);
		this.setOperationStatus("Create New Visitation Succeeded!");
		return SUCCESS;	
	}
	
	public String CreateVisitationFromAppointment() {
		System.out.println("Create Visitation is Executed");
		VisitationDao visitationDao = new VisitationDaoImpl();
		Visitation newVisitation = new Visitation();
		boolean operationSuccess = false;
		try {
			Object queryResult = QueryAppointment(appointmentId);
			if (!(queryResult instanceof String)) {
				Appointment tempAppointment = (Appointment) queryResult;
				java.util.Date utilDate = new java.util.Date();
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				newVisitation.setAppointment(tempAppointment);
				newVisitation.setVisitationDate(tempAppointment
						.getAppointmentDate());
				newVisitation.setPatient(tempAppointment.getPatient());
				newVisitation.setDoctor(tempAppointment.getDoctor());
				newVisitation.setVisitationType("A");
				visitationDao.saveVisitation(newVisitation);
				operationSuccess = true;
				this.setOperationStatus("Create New Visitation From Appointment Succeeded!");
			} else {
				this.setOperationStatus("Create New Visitation From Appointment Failed! Error:"
						+ (String) queryResult);
			}
		} catch (Exception e) {
			this.setOperationStatus("Create New Visitaiton From Appointment Failed! Error:" + e.getMessage());

		} finally {
			visitationDao.cleanup();
		}

		if(operationSuccess == true){
		this.setVisitation(newVisitation);
		this.session.put("CurrentVisitation", visitation);
		this.session.put("AppointmentSearchType", "appID");
		return SUCCESS;
		}
		else{
			return ERROR;
		}
		
	}
	
	
	//TODO update a visitation entry
	public String EditVisitation(){
		System.out.println("SearchVisitation is executed");
		VisitationDao visitationDao = new VisitationDaoImpl();
		Visitation editVisitation = (Visitation)session.get("CurrentVisitation");
		
		try{
			editVisitation = visitationDao.searchVisitation(editVisitation.getVisitationId());
			editVisitation.setSymptomDescription(visitation.getSymptomDescription());
			editVisitation.setDiagnosisResult(visitation.getDiagnosisResult());
			visitationDao.updateVisitation(editVisitation);		
		}catch(Exception e){
			this.setOperationStatus("Update Visitation Failed! Exception Happened:" + e.getMessage());
			visitationDao.cleanup();
			return ERROR;
		}
		finally{
			visitationDao.cleanup();
		}
		this.session.put("CurrentVisitation", editVisitation);
		visitation = editVisitation;
		this.setDiagnosisTestList(visitation.getDiagnosisTestSet());
		return SUCCESS;
	}
	
	//TODO search visitation by patientID, then return a visitation instance
	public String ViewVisitation(){
		return SUCCESS;
	}
	
	public String SearchVisitation(){
		System.out.println("SearchVisitation is executed");
		VisitationDao visitationDao = new VisitationDaoImpl();
		Visitation searchVisitation = new Visitation();
		try{
			 searchVisitation = visitationDao.searchVisitation(visitationId);
			 if (searchVisitation == null)
			 {
				int tempId =((Visitation)session.get("CurrentVisitation")).getVisitationId();
				 searchVisitation = visitationDao.searchVisitation(tempId);
			 }
		}catch(Exception e){
			this.setOperationStatus("View Visitation Failed! Exception Happened:" +e.getMessage());
			visitationDao.cleanup();
			this.setOperationStatus("View Visitation Failed! Exception:"+e.getMessage());
			return ERROR;
		}
		finally{
			visitationDao.cleanup();
		}	
		if (searchVisitation.getDiagnosisTestSet().isEmpty()){
			this.setDiagnosisTestList(null);
		}
		else{
			this.setDiagnosisTestList(searchVisitation.getDiagnosisTestSet());
		}
		if(searchVisitation.getAppointment()!=null)
		{
			this.session.put("AppointmentSearchType", "appID");
		}
		else{
			this.session.put("AppointmentSearchType", null);
		}
		this.setVisitation(searchVisitation);
		this.session.put("CurrentVisitation", visitation);
		this.setOperationStatus("Visitation Found!");
		return SUCCESS;
	}
	
	public Object QueryAppointment(int appointmentId){
		Object queryResult = new Object();
		Appointment resultAppointment = new Appointment();
		
		AppointmentDao appointmentDao = new AppointmentDaoImpl();
		
		try{
			resultAppointment = appointmentDao.searchAppointment(appointmentId);
			if (resultAppointment == null)
			{
				queryResult=  "Appointment Not Found!";
			}
			else{
			session.put("CurrentAppointment", resultAppointment);
			   queryResult = resultAppointment;
			}
		}catch(Exception e){
			queryResult ="Query Appointment Failed, Exception:"	+ e.getMessage();		
		}finally{
			appointmentDao.cleanup();
		}
		
		return queryResult;
	}
	
	
}
