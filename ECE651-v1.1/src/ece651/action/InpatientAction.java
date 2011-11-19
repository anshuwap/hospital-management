package ece651.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import ece651.dao.InpatientDao;
import ece651.dao.InpatientDaoImpl;
import ece651.dao.SurgeryDao;
import ece651.dao.SurgeryDaoImpl;
import ece651.model.Inpatient;
import ece651.model.Surgery;
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
		InpatientDao inpatientDao = new InpatientDaoImpl();
		Inpatient newInpatient = new Inpatient();
		Visitation tempVisitation = new Visitation();
/*		try{
			tempVisitation =(Visitation)session.get("CurrentVisitation");
			newInpatient.setVisitationId(tempVisitation.getVisitationId());
			newInpatient.setIssueDoctor(tempVisitation.getDoctor());
			newInpatient.setPatient(tempVisitation.getPatient());			
			inpatientDao.saveInpatient(newInpatient);		
		}catch(Exception e){
			this.setOperationStatus("Create Inpatient Failed! Exception Happened:" + e.getMessage());
		    return ERROR;
		}*/
		
		//just for test 
		tempVisitation =(Visitation)session.get("CurrentVisitation");
		newInpatient.setVisitationId(tempVisitation.getVisitationId());
		newInpatient.setIssueDoctor(tempVisitation.getDoctor());
		newInpatient.setPatient(tempVisitation.getPatient());
		newInpatient.setInpatientId(tempVisitation.getVisitationId());
		//just for test
		
		inpatient = newInpatient;
		this.session.put("CurrentInpatient",inpatient);
		return SUCCESS;
	}
	
	public String EditInpatient(){
		return SUCCESS;
	}
	
	public String SearchInpatient(){
		return SUCCESS;
	}

}
