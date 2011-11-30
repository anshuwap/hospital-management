package ece651.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import sun.org.mozilla.javascript.internal.Context;
import sun.org.mozilla.javascript.internal.ContextAction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import ece651.dao.AuditTrailDao;
import ece651.dao.AuditTrailDaoImpl;
import ece651.dao.DiagnosisTestDao;
import ece651.dao.DiagnosisTestDaoImpl;
import ece651.model.AuditTrail;
import ece651.model.Patient;

public class AuditTrailAction extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private String tableName;
	private int patientId;
	private List<AuditTrail> auditTrailList;
	private String operationStatus;
	private boolean operationSuccess;
	

	public String getTableName() {
		return tableName;
	}


	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	public int getPatientId() {
		return patientId;
	}


	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}


	public List<AuditTrail> getAuditTrailList() {
		return auditTrailList;
	}


	public String getOperationStatus() {
		return operationStatus;
	}


	public boolean isOperationSuccess() {
		return operationSuccess;
	}


	public void setOperationSuccess(boolean operationSuccess) {
		this.operationSuccess = operationSuccess;
	}


	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}


	public void setAuditTrailList(List<AuditTrail> auditTrailList) {
		this.auditTrailList = auditTrailList;
	}


	public void setSession(Map<String, Object> session) {
		this.session = session;	
		
	}
	
	
	public String SearchAuditTrailByPatientAndTable(){	
			System.out.println("SearchAuditTrailByPatientAndTable is executed");
			this.setOperationSuccess(true);
			AuditTrailDao auditTrailDao = new AuditTrailDaoImpl();
			Patient tempPatient = (Patient)session.get("CurrentPatient");
			try{		
				 auditTrailList = auditTrailDao.searchAuditTrailByPId(tableName, tempPatient.getPatientId());		
				
				if(auditTrailList.isEmpty())
				{
					this.setOperationStatus("No Revision Information Found!");
				}
				else{
					this.setOperationStatus("Revision Information Found!");
					this.session.put("CurrentAuditTrailList", auditTrailList);
				}
				this.setOperationSuccess(true);
			}catch(Exception e){
				
				this.setOperationStatus("Retrieve the Patient's AuditTrail Failed! Exception Happened:" +e.getMessage());
				auditTrailDao.cleanup();
				this.setOperationSuccess(false);       
			}
			finally{
				auditTrailDao.cleanup();
			}	
					
		if(this.isOperationSuccess()){	
		  return SUCCESS;
		}
		else{
			return ERROR;
		}
		
	}
	

	
	
	
}
