package ece651.action;

import java.sql.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import ece651.dao.SurgeryDao;
import ece651.dao.SurgeryDaoImpl;
import ece651.model.Surgery;
import ece651.model.SystemUser;
import ece651.model.Visitation;

public class SurgeryAction extends ActionSupport implements SessionAware,
		RequestAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private Surgery surgery;
	private String operationStatus;
	private String surgeryDate;

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

	public String getSurgeryDate() {
		return surgeryDate;
	}

	public void setSurgeryDate(String surgeryDate) {
		this.surgeryDate = surgeryDate;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;	
	}
	
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
		
	}
	
	public String CreateSurgery(){
		System.out.println("CreateSurgery is executed");
		SurgeryDao surgeryDao = new SurgeryDaoImpl();
		try{
			Visitation tempVisitation =(Visitation)session.get("CurrentVisitation");
			Surgery tempSurgery = surgeryDao.searchSurgery(tempVisitation.getVisitationId());
			if(tempSurgery != null){ //surgery already exist
				surgery = tempSurgery;
				this.session.put("CurrentSurgery",tempSurgery);
				return SUCCESS;
			}
			else{ //surgery not exist
				Surgery newSurgery = new Surgery();
				newSurgery.setVisitation(tempVisitation);
				newSurgery.setIssueDoctor(tempVisitation.getDoctor());
				newSurgery.setPatient(tempVisitation.getPatient());				
				surgeryDao.saveSurgery(newSurgery);	
				surgery = newSurgery;
				this.session.put("CurrentSurgery",newSurgery);
				return SUCCESS;
			}
		}catch(Exception e){
			this.setOperationStatus("Create Surgery Failed! Exception Happened:" + e.getMessage());
		    return ERROR;
		}
		finally{
			surgeryDao.cleanup();
		}
	}
	
	public String EditSurgery(){
		System.out.println("EditSurgery is executed");
		SurgeryDao surgeryDao = new SurgeryDaoImpl();
		Surgery updateSurgery = new Surgery();
		updateSurgery = (Surgery)session.get("CurrentSurgery");
		
		try{	    
			if(((SystemUser)session.get("CurrentUser")).getRoleType().equalsIgnoreCase("N")){
				updateSurgery.setNurse((SystemUser)session.get("CurrentUser"));
				updateSurgery.setArrangementDescription(surgery.getArrangementDescription());
				if(!surgeryDate.trim().isEmpty())
				{
				 Date tempSurgeryDay = Date.valueOf(surgeryDate.substring(0, 10));
				 updateSurgery.setSurgeryDate(tempSurgeryDay);
				}
				else
				{
					updateSurgery.setSurgeryDate(null);
				}
			}			
			if(((SystemUser)session.get("CurrentUser")).getRoleType().equalsIgnoreCase("D")){
				updateSurgery.setSurgetyDoctor((SystemUser)session.get("CurrentUser"));
				updateSurgery.setSurgerySummary(surgery.getSurgerySummary());
			}	
			surgeryDao.updateSurgery(updateSurgery);
			surgery = updateSurgery;
			this.session.put("CurrentSurgery",updateSurgery);

		}catch(Exception e){
			this.setOperationStatus("Update Surgery Failed! Exception Happened:" + e.getMessage());
		    return ERROR;
		}
		finally{
			surgeryDao.cleanup();
		}
		
		if(surgery.getSurgeryDate()!=null)
			   this.setSurgeryDate(surgery.getSurgeryDate().toString().substring(0, 10));
			else {
				this.setSurgeryDate("");
			}
		return SUCCESS;
	}
	
	public String SearchSurgery(){
		System.out.println("SearchSurgery is executed");
		SurgeryDao surgeryDao = new SurgeryDaoImpl();
		try{		
			Visitation tempVisitation =(Visitation)session.get("CurrentVisitation");
			surgery = surgeryDao.searchSurgery(tempVisitation.getSurgery().getSurgeryId());
			this.session.put("CurrentSurgery", surgery);
		}catch(Exception e){
			this.setOperationStatus("View Surgery Failed! Exception Happened:" +e.getMessage());
			return ERROR;
		}
		finally{
			surgeryDao.cleanup();
		}	
		
		if(surgery.getSurgeryDate()!=null)
			   this.setSurgeryDate(surgery.getSurgeryDate().toString().substring(0, 10));
			else {
				this.setSurgeryDate("");
			}
		
		return SUCCESS;
	}

}
