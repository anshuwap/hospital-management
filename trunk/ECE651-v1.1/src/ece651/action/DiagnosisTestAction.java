package ece651.action;

import java.util.ArrayList;
import java.sql.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import ece651.dao.DiagnosisTestDao;
import ece651.dao.DiagnosisTestDaoImpl;
import ece651.dao.VisitationDao;
import ece651.dao.VisitationDaoImpl;
import ece651.model.DiagnosisTest;
import ece651.model.SystemUser;
import ece651.model.Visitation;

public class DiagnosisTestAction extends ActionSupport implements SessionAware,
		RequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map<String, Object> request;
	private DiagnosisTest diagnosisTest;
	private String operationStatus;
	private String testType;
	private int diagnosisTestId;
	private int visitationId;

	public DiagnosisTest getDiagnosisTest() {
		return diagnosisTest;
	}

	public String getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public int getDiagnosisTestId() {
		return diagnosisTestId;
	}

	public void setDiagnosisTestId(int diagnosisTestId) {
		this.diagnosisTestId = diagnosisTestId;
	}

	public int getVisitationId() {
		return visitationId;
	}

	public void setVisitationId(int visitationId) {
		this.visitationId = visitationId;
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
		try{
			tempVisitation =(Visitation)session.get("CurrentVisitation");
			newDiagnosisTest.setVisitationId(tempVisitation.getVisitationId());
			newDiagnosisTest.setDoctor(tempVisitation.getDoctor());
			java.util.Date date = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			newDiagnosisTest.setIssueDate(sqlDate);
			newDiagnosisTest.setPatient(tempVisitation.getPatient());
			newDiagnosisTest.setTestType(diagnosisTest.getTestType());
			diagnosistestDao.saveDiagnosisTest(newDiagnosisTest);			
		}catch(Exception e){
			this.setOperationStatus("Create DiagnosisTest Failed! Exception Happened:" + e.getMessage());
		    return ERROR;
		}	
		finally{
			diagnosistestDao.cleanup();
		}
		diagnosisTest = newDiagnosisTest;
		this.session.put("CurrentDiagnosisTest",diagnosisTest);
		testType = TranslateTestType(newDiagnosisTest.getTestType());
		return SUCCESS;
	}
	
	public String EditDiagnosisTest(){
		System.out.println("UpdateDiagnosisTest is executed");
		DiagnosisTestDao diagnosistestDao = new DiagnosisTestDaoImpl();
		DiagnosisTest updateDiagnosisTest = new DiagnosisTest();
		updateDiagnosisTest = (DiagnosisTest)session.get("CurrentDiagnosisTest");
		SystemUser currentUser = (SystemUser)session.get("CurrentUser");
		
		try{	    
		    updateDiagnosisTest.setNurse(currentUser);
		    updateDiagnosisTest.setTestResultDescription(diagnosisTest.getTestResultDescription());
			diagnosistestDao.updateDiagnosisTest(updateDiagnosisTest);
			this.session.put("CurrentDiagnosisTest",updateDiagnosisTest);
		}catch(Exception e){
			this.setOperationStatus("Update DiagnosisTest Failed! Exception Happened:" + e.getMessage());
		    return ERROR;
		}
		finally{
			diagnosistestDao.cleanup();
		}
		diagnosisTest=updateDiagnosisTest;
		this.setTestType(TranslateTestType(updateDiagnosisTest.getTestType()));
		return SUCCESS;
	}
	
	public String SearchDiagnosisTest(){
		
			System.out.println("SearchDiagnosisTest is executed");
			DiagnosisTestDao diagnosisTestDao = new DiagnosisTestDaoImpl();
			try{
				diagnosisTest = diagnosisTestDao.searchDiagnosisTest(diagnosisTestId, visitationId);
				this.session.put("CurrentDiagnosisTest", diagnosisTest);
			}catch(Exception e){
				this.setOperationStatus("View DiagnosisTest Failed! Exception Happened:" +e.getMessage());
				return ERROR;
			}
			finally{
				diagnosisTestDao.cleanup();
			}	
			String tempString;
			tempString =TranslateTestType(diagnosisTest.getTestType()); 
			this.setTestType(tempString);

			return SUCCESS;

	}
	
	

	public String TranslateTestType(String inputTestType){
        if (inputTestType.equalsIgnoreCase("1"))
        	return "B UltraSound";
        else if (inputTestType.equalsIgnoreCase("2"))
        	return "Blood Test";
        else if (inputTestType.equalsIgnoreCase("3"))
        	return "Urine Test";
        else if (inputTestType.equalsIgnoreCase("4"))
        	return "X Ray";
        else if (inputTestType.equalsIgnoreCase("5"))
        	return "CT Scan";
        else 
        	return "Not Defined";		
	}
	
	
}
