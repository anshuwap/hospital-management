package ece651.action;

import java.sql.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import ece651.dao.PrescriptionDao;
import ece651.dao.PrescriptionDaoImpl;
import ece651.dao.SurgeryDao;
import ece651.dao.SurgeryDaoImpl;
import ece651.model.Patient;
import ece651.model.Prescription;
import ece651.model.Surgery;
import ece651.model.SystemUser;
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
		Surgery newSurgery;
		try{
			Visitation tempVisitation =(Visitation)session.get("CurrentVisitation");
			newSurgery = surgeryDao.searchSurgery(tempVisitation.getVisitationId());
			if(newSurgery != null){ //surgery already exist
				surgery = newSurgery;
				this.session.put("CurrentSurgery",newSurgery);
				return SUCCESS;
			}
			else{ //surgery not exist
				newSurgery = new Surgery();
				newSurgery.setVisitation(tempVisitation);
				java.util.Date date = new java.util.Date();
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				newSurgery.setSurgeryDate(sqlDate);
				newSurgery.setIssueDoctor(tempVisitation.getDoctor());
				newSurgery.setPatient(tempVisitation.getPatient());				
				surgeryDao.saveSurgery(newSurgery);	
				this.session.put("CurrentSurgery",newSurgery);
			}
		}catch(Exception e){
			this.setOperationStatus("Create Surgery Failed! Exception Happened:" + e.getMessage());
		    return ERROR;
		}
		finally{
			surgeryDao.cleanup();
		}
		surgery = newSurgery;
		return SUCCESS;
	}
	
	public String EditSurgery(){
		return SUCCESS;
	}
	
	public String SearchSurgery(){
		return SUCCESS;
	}

}
