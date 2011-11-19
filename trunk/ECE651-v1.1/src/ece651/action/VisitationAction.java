package ece651.action;

import java.sql.Date;
import java.util.*;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
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


	public void setRequest(Map<String, Object> request) {
		this.request = request;
		
	}


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
		    return ERROR;
		}
		finally{
			visitationDao.cleanup();
		}
		this.session.put("CurrentVisitation", editVisitation);
		visitation = editVisitation;
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
		}catch(Exception e){
			this.setOperationStatus("View Visitation Failed! Exception Happened:" +e.getMessage());
			return ERROR;
		}
		finally{
			visitationDao.cleanup();
		}	
		
		//Object searchDiagnosisTestList = QueryDiagnosisTest(searchVisitation);
//		if(searchDiagnosisTestList instanceof String){
//			this.setQueryDiagnosisTestList("Visitation Not Found, Reason: "+(String)searchDiagnosisTestList);
//		}
//		else {
//			this.setDiagnosisTestList((ArrayList<Visitation>)searchDiagnosisTestList);
//			this.session.put("DiagnosisTestList", diagnosisTestList);
//		}	
		this.setDiagnosisTestList(searchVisitation.getDiagnosisTestSet());
		this.setVisitation(searchVisitation);
		this.session.put("CurrentVisitation", visitation);
		return SUCCESS;
	}
	
	
	/*private Object QueryDiagnosisTest(Visitation currentVisitation){
		ArrayList<DiagnosisTest> resultDiagnosisTestList;
		try{
			DiagnosisTestDao diagnosisTestDao = new DiagnosisTestDaoImpl();
			resultDiagnosisTestList = (ArrayList<DiagnosisTest>) diagnosisTestDao.searchDiagnosisTest(currentVisitation);
			if (resultDiagnosisTestList.isEmpty()){
				return "DiagnosisTest Not Found";
			}
		} catch(Exception e){
			return "Exception Happened"+e.getMessage();		
		}		
		return resultDiagnosisTestList;
	}*/
	
	
	
}
