package ece651.action;

import java.sql.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import ece651.dao.InpatientDao;
import ece651.dao.InpatientDaoImpl;
import ece651.dao.SystemUserDao;
import ece651.dao.SystemUserDaoImpl;
import ece651.model.Inpatient;
import ece651.model.SystemUser;
import ece651.model.Visitation;

public class InpatientAction extends ActionSupport implements SessionAware,
		RequestAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private Inpatient inpatient;
	private String operationStatus;
	private String inpatientDay;
	private String dischargeDay;

	public String getInpatientDay() {
		return inpatientDay;
	}

	public void setInpatientDay(String inpatientDay) {
		this.inpatientDay = inpatientDay;
	}

	public String getDischargeDay() {
		return dischargeDay;
	}

	public void setDischargeDay(String dischargeDay) {
		this.dischargeDay = dischargeDay;
	}

	public Inpatient getInpatient() {
		return inpatient;
	}

	public void setInpatient(Inpatient inpatient) {
		this.inpatient = inpatient;
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
	
	public String CreateInpatient(){
		System.out.println("CreateInpatient is executed");
		InpatientDao inpatientDao = new InpatientDaoImpl();
		inpatient = new Inpatient();
		Visitation visitation =(Visitation)session.get("CurrentVisitation");
		inpatient.setVisitation(visitation);
		inpatient.setIssueDoctor(visitation.getDoctor());
		inpatient.setPatient(visitation.getPatient());
		
		try{
			inpatientDao.saveInpatient(inpatient);	
			this.session.put("CurrentInpatient",inpatient);
		}catch(Exception e){
			this.setOperationStatus("Create Inpatient Failed! Exception Happened:" + e.getMessage());
			return ERROR;
		}finally{
			inpatientDao.cleanup();
		}
		
		return SUCCESS;
	}
	
	public String EditInpatient(){
		System.out.println("EditInpatient is executed");
		InpatientDao inpatientDao = new InpatientDaoImpl();
		SystemUserDao userdao = new SystemUserDaoImpl();
		try{
			Inpatient inpInSession =(Inpatient)session.get("CurrentInpatient");
			if(((SystemUser)session.get("CurrentUser")).getRoleType().equalsIgnoreCase("D")){
			inpInSession.setInpatientDoctor((SystemUser)session.get("CurrentUser"));
			inpInSession.setDischargeSummary(inpatient.getDischargeSummary());	
			}
		else if (((SystemUser)session.get("CurrentUser")).getRoleType().equalsIgnoreCase("N")){
				inpInSession.setNurse((SystemUser)session.get("CurrentUser"));
				Date tempInpatientDay = Date.valueOf(inpatientDay);
				Date tempDischargeDay = Date.valueOf(dischargeDay);
				inpInSession.setInpatientDate(tempInpatientDay);	
				inpInSession.setDischargetDate(tempDischargeDay);
				inpInSession.setArrangementDescription(inpatient.getArrangementDescription());
			}			
			inpatientDao.updateInpatient(inpInSession);
			this.session.put("CurrentInpatient",inpInSession);
			inpatient = inpInSession;
		}catch(Exception e){
			this.setOperationStatus("Edit Inpatient Failed! Exception Happened:" + e.getMessage());
			return ERROR;
		}finally{
			inpatientDao.cleanup();
		}
		if(inpatient.getInpatientDate()!=null)
		   this.setInpatientDay(inpatient.getInpatientDate().toString().substring(0, 10));
		if(inpatient.getDischargetDate()!=null)
		   this.setDischargeDay(inpatient.getDischargetDate().toString().substring(0, 10));
				
		return SUCCESS;
	}
	
	public String SearchInpatient(){
		System.out.println("SearchInpatient is executed");
		
		Visitation visitation =(Visitation)session.get("CurrentVisitation");
		if((inpatient = visitation.getInpatient())!=null){
			this.session.put("CurrentInpatient",inpatient);
		}
		//if it is null, try to get from database
		InpatientDao inpatientDao = new InpatientDaoImpl();
		try{
			inpatient = inpatientDao.searchInpatient(inpatient.getInpatientId());	
			this.session.put("CurrentInpatient",inpatient);
		}catch(Exception e){
			this.setOperationStatus("Create Inpatient Failed! Exception Happened:" + e.getMessage());
			return ERROR;
		}finally{
			inpatientDao.cleanup();
		}
		if(inpatient.getInpatientDate()!=null)
		this.setInpatientDay(inpatient.getInpatientDate().toString().substring(0, 10));
		if(inpatient.getDischargetDate()!=null)
		this.setDischargeDay(inpatient.getDischargetDate().toString().substring(0, 10));
		
		return SUCCESS;
	}

}
