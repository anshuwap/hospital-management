package ece651.action;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import ece651.dao.DiagnosisTestDao;
import ece651.dao.DiagnosisTestDaoImpl;
import ece651.dao.VisitationDao;
import ece651.dao.VisitationDaoImpl;
import ece651.model.DiagnosisTest;
import ece651.model.Visitation;

public class DiagnosisTestAction extends ActionSupport implements SessionAware,
		RequestAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private DiagnosisTest diagnosisTest;
	private String operationStatus;

	public DiagnosisTest getDiagnosisTest() {
		return diagnosisTest;
	}

	public String getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}

	public void setDiagnosisTest(DiagnosisTest diagnosisTest) {
		this.diagnosisTest = diagnosisTest;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;	
	}
	
	public void setRequest(Map<String, Object> request) {
		this.request = request;
		
	}
	
	public String CreateDiagnosisTest(){
		System.out.println("CreateDiagnosisTest is executed");
		DiagnosisTestDao diagnosistestDao = new DiagnosisTestDaoImpl();
		DiagnosisTest newDiagnosisTest = new DiagnosisTest();
		Visitation tempVisitation = new Visitation();
		/*try{
			tempVisitation =(Visitation)session.get("CurrentVisitation");
			newDiagnosisTest.setVisitationId(tempVisitation.getVisitationId());
			newDiagnosisTest.setDoctor(tempVisitation.getDoctor());
			newDiagnosisTest.setIssueDate(new Date());
			newDiagnosisTest.setPatient(tempVisitation.getPatient());			
			diagnosistestDao.saveDiagnosisTest(newDiagnosisTest);			
		}catch(Exception e){
			this.setOperationStatus("Create DiagnosisTest Failed! Exception Happened:" + e.getMessage());
		    return ERROR;
		}*/
		
		//just for test 
		tempVisitation =(Visitation)session.get("CurrentVisitation");
		newDiagnosisTest.setVisitationId(tempVisitation.getVisitationId());
		newDiagnosisTest.setDoctor(tempVisitation.getDoctor());
		newDiagnosisTest.setIssueDate(new Date());
		newDiagnosisTest.setPatient(tempVisitation.getPatient());
		newDiagnosisTest.setTestRequestDescription("");
		//just for test
		
		diagnosisTest = newDiagnosisTest;
		this.session.put("CurrentDiagnosisTest",diagnosisTest);
		return SUCCESS;
		}
	
	public String EditDiagnosisTest(){
		return SUCCESS;
	}
	
	public String SearchDiagnosisTest(){
		return SUCCESS;
	}

}
