package ece651.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import ece651.dao.PrescriptionDao;
import ece651.dao.PrescriptionDaoImpl;
import ece651.model.Prescription;
import ece651.model.Visitation;

public class PrescriptionAction extends ActionSupport implements SessionAware,
		RequestAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private String operationStatus;
	private Prescription prescription;
	private int prescriptionId;

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

	public int getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;	
	}
	
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
		
	}
	
	public String CreatePrescription(){
		System.out.println("CreatePrescription is executed");
		PrescriptionDao prescriptionDao = new PrescriptionDaoImpl();
		Prescription newPrescription = new Prescription();
		Visitation tempVisitation = new Visitation();
		try{
			tempVisitation =(Visitation)session.get("CurrentVisitation");
            newPrescription.setDoctor(tempVisitation.getDoctor());
		    newPrescription.setPatient(tempVisitation.getPatient());
		   
			java.util.Date date = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			newPrescription.setPrescriptionDate(sqlDate);
		    newPrescription.setVisitation(tempVisitation);
		    newPrescription.setPrescriptionId(tempVisitation.getVisitationId());			
			prescriptionDao.savePrescription(newPrescription);	
			this.session.put("CurrentPrescription",newPrescription);
		}catch(Exception e){
			this.setOperationStatus("Create Prescription Failed! Exception Happened:" + e.getMessage());
		    return ERROR;
		}
		
//		//just for test 
//		tempVisitation =(Visitation)session.get("CurrentVisitation");
//		newPrescription.setDoctor(tempVisitation.getDoctor());
//		newPrescription.setPatient(tempVisitation.getPatient());
//		newPrescription.setPrescriptionDate(new Date());
//		newPrescription.setVisitation(tempVisitation);
//		newPrescription.setPrescriptionId(tempVisitation.getVisitationId());	
//		//just for test	
		prescription = newPrescription;
		
		return SUCCESS;
		
	}
	
	public String EditPrescription(){
		System.out.println("UpdatePrescription is executed");
		PrescriptionDao prescriptionDao = new PrescriptionDaoImpl();
		Prescription updatePrescription = new Prescription();
		updatePrescription = (Prescription)session.get("CurrentPrescription");
		try{	    
		    updatePrescription.setPrescriptionDescription(prescription.getPrescriptionDescription());
			prescriptionDao.updatePrescription(updatePrescription);
			this.session.put("CurrentPrescription",updatePrescription);
		}catch(Exception e){
			this.setOperationStatus("Update Prescription Failed! Exception Happened:" + e.getMessage());
		    return ERROR;
		}
		finally{
			prescriptionDao.cleanup();
		}
		prescription= updatePrescription;
		
		return SUCCESS;
	}
	
	public String SearchPrescription(){
		
		System.out.println("SearchPrescription is executed");
		PrescriptionDao prescriptionDao = new PrescriptionDaoImpl();
		try{		
			prescription = prescriptionDao.searchPrescription(prescriptionId);
			this.session.put("CurrentPrescription", prescription);
		}catch(Exception e){
			this.setOperationStatus("View Prescription Failed! Exception Happened:" +e.getMessage());
			return ERROR;
		}
		finally{
			prescriptionDao.cleanup();
		}	

		return SUCCESS;
	}

}
