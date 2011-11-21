package ece651.action;

import java.sql.Date;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import ece651.dao.InpatientDairyDao;
import ece651.dao.InpatientDairyDaoImpl;
import ece651.dao.InpatientDao;
import ece651.dao.InpatientDaoImpl;
import ece651.dao.PrescriptionDao;
import ece651.dao.PrescriptionDaoImpl;
import ece651.dao.SystemUserDao;
import ece651.dao.SystemUserDaoImpl;
import ece651.model.Inpatient;
import ece651.model.InpatientDairy;
import ece651.model.Prescription;
import ece651.model.SystemUser;
import ece651.model.Visitation;

public class InpatientDairyAction extends ActionSupport implements SessionAware,
		RequestAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private InpatientDairy inpatientDairy;
	private String operationStatus;
	private int inpatientDairyId;
	private int inpatientId; 
	

	public InpatientDairy getInpatientDairy() {
		return inpatientDairy;
	}

	public void setInpatientDairy(InpatientDairy inpatientDairy) {
		this.inpatientDairy = inpatientDairy;
	}

	public String getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}

	public int getInpatientDairyId() {
		return inpatientDairyId;
	}

	public void setInpatientDairyId(int inpatientDairyId) {
		this.inpatientDairyId = inpatientDairyId;
	}

	public int getInpatientId() {
		return inpatientId;
	}

	public void setInpatientId(int inpatientId) {
		this.inpatientId = inpatientId;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;	
	}
	
	public void setRequest(Map<String, Object> request) {
		this.request = request;
		
	}
	
	public String CreateInpatientDairy(){
		System.out.println("CreateInpatientDairy is executed");
		InpatientDairyDao inpatientDairyDao = new InpatientDairyDaoImpl();
		
		inpatientDairy = new InpatientDairy();
		Inpatient inpatient =(Inpatient)session.get("CurrentInpatient");
		inpatientDairy.setInpatientId(inpatient.getInpatientId());
		inpatientDairy.setPatient(inpatient.getPatient());
		inpatientDairy.setNurse((SystemUser) session.get("CurrentUser"));
		inpatientDairy.setRecordDate(new Date(System.currentTimeMillis()));
		
		try{	
			inpatientDairyDao.saveInpatientDairy(inpatientDairy);
			this.session.put("CurrentInpatientDairy",inpatientDairy);
		}catch(Exception e){
			this.setOperationStatus("Create InpatientDairy Failed! Exception Happened:" + e.getMessage());
		    return ERROR;
		}
		finally{
			inpatientDairyDao.cleanup();
		}
		
		return SUCCESS;
	}
	
	public String EditInpatientDairy(){
		System.out.println("EditInpatientDairy is executed");
		InpatientDairyDao inpatientDairyDao = new InpatientDairyDaoImpl();
		
		InpatientDairy inpdInSession =(InpatientDairy)session.get("CurrentInpatientDairy");
		inpdInSession.setRecordDate(new Date(System.currentTimeMillis()));
		inpdInSession.setDairyDescription(inpatientDairy.getDairyDescription());
		
		try{
			inpatientDairyDao.updateInpatientDairy(inpdInSession);
			this.session.put("CurrentInpatientDairy",inpdInSession);
		}catch(Exception e){
			this.setOperationStatus("Edit InpatientDairy Failed! Exception Happened:" + e.getMessage());
		    return ERROR;
		}finally{
			inpatientDairyDao.cleanup();
		}
		inpatientDairy = inpdInSession;	
		return SUCCESS;
	}
	
	public String SearchInpatientDairy(){		
		System.out.println("SearchInpatientDairy is executed");
		InpatientDairyDao inpatientDairyDao = new InpatientDairyDaoImpl();
		try{		
			inpatientDairy = inpatientDairyDao.searchInpatientDairy(inpatientDairyId, inpatientId);
			this.session.put("CurrentInpatientDairy", inpatientDairy);
		}catch(Exception e){
			this.setOperationStatus("View Prescription Failed! Exception Happened:" +e.getMessage());
			inpatientDairyDao.cleanup();
			return ERROR;
		}
		finally{
			inpatientDairyDao.cleanup();
		}	
		return SUCCESS;
	}

}
