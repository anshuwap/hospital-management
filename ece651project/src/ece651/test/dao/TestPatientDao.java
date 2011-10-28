package ece651.test.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

import ece651.dao.DAOException;
import ece651.dao.PatientDaoImpl;
import ece651.model.Patient;

public class TestPatientDao extends TestCase {

	public void testsavePatient() throws DAOException{
		PatientDaoImpl patientdao = new PatientDaoImpl();
		
		Patient patient = new Patient();
		patient.setPatientName("Bill Gates");
		patient.setGender("M");
		String bDate = "1980-01-01";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = null;
		try {
			birthday = sdf.parse(bDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		patient.setBirthday(birthday);
		patient.setHealthCardId("OHIP123");
		patient.setMedication("VC");
		patient.setAllergy("fish");

		System.out.println("new Patient before save is:"+patient);
		patientdao.savePatient(patient);
		System.out.println("new Patient after save is:"+patient);
	}

	public void testsearchPatient() throws DAOException{
		PatientDaoImpl patientdao = new PatientDaoImpl();
		Patient patient = patientdao.searchPatient(1);
		System.out.println("Patient is:"+patient);
	}


	public void testupdatePatient() throws DAOException{
		PatientDaoImpl patientdao = new PatientDaoImpl();
		Patient patient = patientdao.searchPatient(1);
		patient.setHealthCardId("OHIP8888");
		patientdao.updatePatient(patient);
		System.out.print("Patient is:"+patient);
	}


	public void testdeletePatient() throws DAOException{
		PatientDaoImpl patientdao = new PatientDaoImpl();
		Patient patient = patientdao.searchPatient(1);
		System.out.print("before delete Patient is:"+patient);
		patientdao.deletePatient(patient);
		patient = patientdao.searchPatient(1);
		System.out.print("after delete Patient is:"+patient);
	}
	
	public void testsavePatient2() throws DAOException{
		PatientDaoImpl patientdao = new PatientDaoImpl();
		
		Patient patient = new Patient();
		patient.setPatientName("Bill Gates");
		patient.setGender("M");
		String bDate = "1980-01-01";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = null;
		try {
			birthday = sdf.parse(bDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		patient.setBirthday(birthday);
		patient.setHealthCardId("OHIP123");
		patient.setMedication("VC");
		patient.setAllergy("fish");

		System.out.println("new Patient before save is:"+patient);
		patientdao.savePatient(patient);
		System.out.println("new Patient after save is:"+patient);
	}

}
