package ece651.action;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import ece651.dao.PrescriptionDao;
import ece651.dao.PrescriptionDaoImpl;
import ece651.dao.SurgeryDao;
import ece651.dao.SurgeryDaoImpl;
import ece651.model.Prescription;
import ece651.model.Surgery;
import ece651.model.Visitation;

public class SurgeryAction extends ActionSupport implements SessionAware,
		RequestAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private Surgery surgery;
	private String operationStatus;

	public Surgery getSurgery() {
		return surgery;
	}

	public void setSurgery(Surgery surgery) {
		this.surgery = surgery;
	}

	public String getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;	
	}
	
	public void setRequest(Map<String, Object> request) {
		this.request = request;
		
	}
	
	public String CreateSurgery(){
		System.out.println("CreateSurgery is executed");
		SurgeryDao surgeryDao = new SurgeryDaoImpl();
		Surgery newSurgery = new Surgery();
		Visitation tempVisitation = new Visitation();
/*		try{
			tempVisitation =(Visitation)session.get("CurrentVisitation");
			newSurgery.setVisitationId(tempVisitation.getVisitationId());
			newSurgery.setIssueDoctor(tempVisitation.getDoctor());
			newSurgery.setPatient(tempVisitation.getPatient());				
			surgeryDao.saveSurgery(newSurgery);		
		}catch(Exception e){
			this.setOperationStatus("Create Surgery Failed! Exception Happened:" + e.getMessage());
		    return ERROR;
		}*/
		
		//just for test 
		tempVisitation =(Visitation)session.get("CurrentVisitation");
		newSurgery.setVisitationId(tempVisitation.getVisitationId());
		newSurgery.setIssueDoctor(tempVisitation.getDoctor());
		newSurgery.setPatient(tempVisitation.getPatient());		
		//just for test
		
		surgery = newSurgery;
		this.session.put("CurrentSurgery",surgery);
		return SUCCESS;
	}
	
	public String EditSurgery(){
		return SUCCESS;
	}
	
	public String SearchSurgery(){
		return SUCCESS;
	}

}
