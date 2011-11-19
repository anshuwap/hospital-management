package ece651.action;

import java.sql.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import ece651.dao.InpatientDairyDao;
import ece651.dao.InpatientDairyDaoImpl;
import ece651.dao.PrescriptionDao;
import ece651.dao.PrescriptionDaoImpl;
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

	public void setSession(Map<String, Object> session) {
		this.session = session;	
	}
	
	public void setRequest(Map<String, Object> request) {
		this.request = request;
		
	}
	
	public String CreateInpatientDairy(){
		System.out.println("CreateInpatientDairy is executed");
		InpatientDairyDao inpatientDairyDao = new InpatientDairyDaoImpl();
		InpatientDairy newInpatientDairy = new InpatientDairy();
		Inpatient tempInpatient = new Inpatient();
/*		try{
			tempInpatient =(Inpatient)session.get("CurrentInpatient");
		    newInpatientDairy.setInpatientId(tempInpatient.getInpatientId());
		    newInpatientDairy.setPatient(tempInpatient.getPatient());
		    newInpatientDairy.setNurse((SystemUser) session.get("CurrentUser"));
		    newInpatientDairy.setRecordDate(new Date());		
			inpatientDairyDao.saveInpatientDairy(newInpatientDairy);	
		}catch(Exception e){
			this.setOperationStatus("Create InpatientDairy Failed! Exception Happened:" + e.getMessage());
		    return ERROR;
		}*/
		
		//just for test 
		tempInpatient =(Inpatient)session.get("CurrentInpatient");
	    newInpatientDairy.setInpatientId(tempInpatient.getInpatientId());
	    newInpatientDairy.setPatient(tempInpatient.getPatient());
	    newInpatientDairy.setNurse((SystemUser) session.get("CurrentUser"));
	    newInpatientDairy.setRecordDate(new Date());
		//just for test
		
		inpatientDairy = newInpatientDairy;
		this.session.put("CurrentInpatientDairy",inpatientDairy);
		return SUCCESS;
	}
	
	public String EditInpatientDairy(){
		return SUCCESS;
	}
	
	public String SearchInpatientDairy(){
		return SUCCESS;
	}

}
