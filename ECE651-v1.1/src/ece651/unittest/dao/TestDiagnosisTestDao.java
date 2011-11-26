package ece651.unittest.dao;

import java.sql.Date;
import junit.framework.TestCase;

import ece651.dao.DAOException;
import ece651.dao.PatientDaoImpl;
import ece651.dao.SystemUserDaoImpl;
import ece651.dao.DiagnosisTestDaoImpl;
import ece651.model.Patient;
import ece651.model.DiagnosisTest;
import ece651.model.SystemUser;

public class TestDiagnosisTestDao extends TestCase {
	
	public static Integer DiaId;
	
	public void  testsaveDiagnosisTest() throws DAOException{
		DiagnosisTestDaoImpl diagnosisTestDao = new DiagnosisTestDaoImpl();
		PatientDaoImpl patientdao = new PatientDaoImpl();		
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		
		SystemUser doctor = userdao.searchUserBySystemUserId(2);
		SystemUser nurse = userdao.searchUserBySystemUserId(3);
		Patient patient = patientdao.searchPatient(1);
		
		DiagnosisTest diagnosisTest = new DiagnosisTest();
		diagnosisTest.setVisitationId(1);
		diagnosisTest.setPatient(patient);
		diagnosisTest.setDoctor(doctor);
		diagnosisTest.setNurse(nurse);
		diagnosisTest.setTestType("A");
		//Date iDate = Date.valueOf("2011-08-10");
		diagnosisTest.setIssueDate(new Date(System.currentTimeMillis()));
		diagnosisTest.setTestRequestDescription("X ray");
		diagnosisTest.setTestResultDescription("good");
		
		System.out.print("New DiagnosisTest is:"+diagnosisTest);
		diagnosisTestDao.saveDiagnosisTest(diagnosisTest);
		System.out.print("After save() new DiagnosisTest is:"+diagnosisTest);
		DiaId = diagnosisTest.getDiagnosisTestId();
	}
	
	public void  testsearchDiagnosisTest() throws DAOException{
		DiagnosisTestDaoImpl diagnosisTestDao = new DiagnosisTestDaoImpl();
		
		DiagnosisTest diagnosisTest = diagnosisTestDao.searchDiagnosisTest(DiaId,1);
		System.out.print("DiagnosisTest is:"+diagnosisTest);
	}

	public void  testupdateDiagnosisTest() throws DAOException{
		DiagnosisTestDaoImpl diagnosisTestDao = new DiagnosisTestDaoImpl();
		
		DiagnosisTest diagnosisTest = diagnosisTestDao.searchDiagnosisTest(DiaId,1);
		System.out.print("Before update DiagnosisTest is:"+diagnosisTest);
		diagnosisTest.setTestResultDescription("very good");
		diagnosisTestDao.updateDiagnosisTest(diagnosisTest);
		diagnosisTest = diagnosisTestDao.searchDiagnosisTest(DiaId,1);
		System.out.print("After update DiagnosisTest is:"+diagnosisTest);
	}

}
