package ece651.action;

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
		String result;
		InpatientDao inpatientDao = new InpatientDaoImpl();
		
		Visitation visitation =(Visitation)session.get("CurrentVisitation");
		inpatient.setVisitation(visitation);
		inpatient.setIssueDoctor(visitation.getDoctor());
		inpatient.setPatient(visitation.getPatient());
		
		try{
			inpatientDao.saveInpatient(inpatient);	
			this.session.put("CurrentInpatient",inpatient);
			result = SUCCESS;
		}catch(Exception e){
			this.setOperationStatus("Create Inpatient Failed! Exception Happened:" + e.getMessage());
			result = ERROR;
		}finally{
			inpatientDao.cleanup();
		}
		
		return result;
	}
	
	public String EditInpatient(){
		System.out.println("EditInpatient is executed");
		String result;
		InpatientDao inpatientDao = new InpatientDaoImpl();
		SystemUserDao userdao = new SystemUserDaoImpl();
		
		try{
			Inpatient inpInSession =(Inpatient)session.get("CurrentInpatient");
			
			SystemUser inpDoctor = userdao.searchUserBySystemUserId(inpatient.getInpatientDoctor().getSystemUserId());
			SystemUser inpNurse = userdao.searchUserBySystemUserId(inpatient.getNurse().getSystemUserId());
			
			inpInSession.setInpatientDate(inpatient.getInpatientDate());	
			inpInSession.setDischargetDate(inpatient.getDischargetDate());
			inpInSession.setArrangementDescription(inpatient.getArrangementDescription());
			inpInSession.setDischargeSummary(inpatient.getDischargeSummary());
			inpInSession.setInpatientDoctor(inpDoctor);
			inpInSession.setNurse(inpNurse);
					
			inpatientDao.updateInpatient(inpInSession);
			this.session.put("CurrentInpatient",inpInSession);
			result = SUCCESS;
		}catch(Exception e){
			this.setOperationStatus("Edit Inpatient Failed! Exception Happened:" + e.getMessage());
			result = ERROR;
		}finally{
			inpatientDao.cleanup();
		}
				
		return result;
	}
	
	public String SearchInpatient(){
		System.out.println("SearchInpatient is executed");
		String result;
		InpatientDao inpatientDao = new InpatientDaoImpl();
		
		Visitation visitation =(Visitation)session.get("CurrentVisitation");
		if((inpatient = visitation.getInpatient())!=null){
			this.session.put("CurrentInpatient",inpatient);
			return SUCCESS;
		}
		//if it is null, try to get from database
		try{
			inpatientDao.searchInpatient(inpatient.getInpatientId());	
			this.session.put("CurrentInpatient",inpatient);
			result = SUCCESS;
		}catch(Exception e){
			this.setOperationStatus("Create Inpatient Failed! Exception Happened:" + e.getMessage());
			result = ERROR;
		}finally{
			inpatientDao.cleanup();
		}
		
		return result;
	}

}
