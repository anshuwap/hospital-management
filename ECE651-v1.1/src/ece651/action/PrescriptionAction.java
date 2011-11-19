package ece651.action;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import ece651.dao.DiagnosisTestDao;
import ece651.dao.DiagnosisTestDaoImpl;
import ece651.dao.PrescriptionDao;
import ece651.dao.PrescriptionDaoImpl;
import ece651.model.DiagnosisTest;
import ece651.model.Prescription;
import ece651.model.Visitation;

public class PrescriptionAction extends ActionSupport implements SessionAware,
		RequestAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private String operationStatus;
	private Prescription prescription;

	public String getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;	
	}
	
	public void setRequest(Map<String, Object> request) {
		this.request = request;
		
	}
	
	public String CreatePrescription(){
		System.out.println("CreatePrescription is executed");
		PrescriptionDao prescriptionDao = new PrescriptionDaoImpl();
		Prescription newPrescription = new Prescription();
		Visitation tempVisitation = new Visitation();
/*		try{
			tempVisitation =(Visitation)session.get("CurrentVisitation");
            newPrescription.setDoctor(tempVisitation.getDoctor());
		    newPrescription.setPatient(tempVisitation.getPatient());
		    newPrescription.setPrescriptionDate(new Date());
		    newPrescription.setVisitation(tempVisitation);
		    newPrescription.setPrescriptionId(tempVisitation.getVisitationId());			
			prescriptionDao.savePrescription(newPrescription);		
		}catch(Exception e){
			this.setOperationStatus("Create Prescription Failed! Exception Happened:" + e.getMessage());
		    return ERROR;
		}*/
		
		//just for test 
		tempVisitation =(Visitation)session.get("CurrentVisitation");
		newPrescription.setDoctor(tempVisitation.getDoctor());
		newPrescription.setPatient(tempVisitation.getPatient());
		newPrescription.setPrescriptionDate(new Date());
		newPrescription.setVisitation(tempVisitation);
		newPrescription.setPrescriptionId(tempVisitation.getVisitationId());	
		//just for test
		
		prescription = newPrescription;
		this.session.put("CurrentPrescription",prescription);
		
		return SUCCESS;
		
	}
	
	public String EditPrescription(){
		return SUCCESS;
	}
	
	public String SearchPrescription(){
		return SUCCESS;
	}

}
