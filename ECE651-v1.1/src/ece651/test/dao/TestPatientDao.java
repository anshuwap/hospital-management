package ece651.test.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import ece651.dao.DAOException;
import ece651.dao.PatientDaoImpl;
import ece651.model.Patient;

public class TestPatientDao extends TestCase {

	public static Integer patientId;
	
	public void testsavePatient() throws DAOException{
		PatientDaoImpl patientdao = new PatientDaoImpl();
		
		Patient patient = new Patient();
		patient.setPatientName("Bill Gates");
		patient.setGender("M");
		Date birthday = Date.valueOf("2011-08-10");
		patient.setBirthday(birthday);
		patient.setHealthCardId("OHIP123456");
		patient.setMedication("VC");
		patient.setAllergy("fish");
		
		patientdao.savePatient(patient);
		patientId = patient.getPatientId();
		assertNotSame(0, patientId);
	}

	public void testsearchPatient() throws DAOException{
		PatientDaoImpl patientdao = new PatientDaoImpl();
		Patient patient = patientdao.searchPatient(patientId);
		assertNotNull(patient);
		assertEquals("OHIP123456", patient.getHealthCardId());
	}

	public void testsearchPatient2() throws DAOException{
		PatientDaoImpl patientdao = new PatientDaoImpl();
		Patient patient = patientdao.searchPatientByHId("OHIP123456");
		assertNotNull(patient);
		assertEquals("OHIP123456", patient.getHealthCardId());
	}
	
	public void testsearchAllPatient() throws DAOException{
		PatientDaoImpl patientdao = new PatientDaoImpl();
		List<Patient> patientList = new ArrayList<Patient>();
		patientList = patientdao.searchAllPatient();
		assertNotSame(0, patientList.size());
	}
	
	public void testupdatePatient() throws DAOException{
		PatientDaoImpl patientdao = new PatientDaoImpl();
		Patient patient = patientdao.searchPatient(patientId);
		patient.setHealthCardId("OHIP1234567");
		patientdao.updatePatient(patient);
		
		Patient patientdb = patientdao.searchPatient(patientId);
		assertEquals("OHIP1234567", patientdb.getHealthCardId());
	}


	public void testdeletePatient() throws DAOException{
		PatientDaoImpl patientdao = new PatientDaoImpl();
		Patient patient = patientdao.searchPatient(patientId);
		patientdao.deletePatient(patient);
		
		Patient patientdb = patientdao.searchPatient(patientId);
		assertNull(patientdb);
	}

}
