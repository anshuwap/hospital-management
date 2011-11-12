package ece615.test.dao;

import java.sql.Date;
import junit.framework.TestCase;

import ece651.dao.DAOException;
import ece651.dao.PatientDaoImpl;
import ece651.dao.SystemUserDaoImpl;
import ece651.dao.InpatientDaoImpl;
import ece651.model.Patient;
import ece651.model.Inpatient;
import ece651.model.SystemUser;

public class TestInpatientDao extends TestCase {
	
	public void  testsaveInpatient() throws DAOException{
		InpatientDaoImpl inpatientDao = new InpatientDaoImpl();
		PatientDaoImpl patientdao = new PatientDaoImpl();		
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		
		SystemUser doctor = userdao.searchUserBySystemUserId(2);
		SystemUser nurse = userdao.searchUserBySystemUserId(3);
		Patient patient = patientdao.searchPatient(1);
		
		Inpatient inpatient = new Inpatient();
		inpatient.setInpatientId(1);
		inpatient.setVisitationId(1);
		inpatient.setPatient(patient);
		inpatient.setIssueDoctor(doctor);
		inpatient.setInpatientDoctor(doctor);
		inpatient.setNurse(nurse);
		
		Date iDate = Date.valueOf("2011-08-10");
		inpatient.setInpatientDate(iDate);
		Date dDate = Date.valueOf("2011-08-15");
		inpatient.setDischargetDate(dDate);
		inpatient.setArrangementDescription("5 days");
		inpatient.setDischargeSummary("good");
		
		System.out.print("New Inpatient is:"+inpatient);
		inpatientDao.saveInpatient(inpatient);
		System.out.print("After save() new Inpatient is:"+inpatient);
	}
	
	public void  testsearchInpatient() throws DAOException{
		InpatientDaoImpl inpatientDao = new InpatientDaoImpl();
		
		Inpatient inpatient = inpatientDao.searchInpatient(1,1);
		System.out.print("Inpatient is:"+inpatient);
	}

	public void  testupdateInpatient() throws DAOException{
		InpatientDaoImpl inpatientDao = new InpatientDaoImpl();
		
		Inpatient inpatient = inpatientDao.searchInpatient(1,1);
		System.out.print("Before update Inpatient is:"+inpatient);
		inpatient.setArrangementDescription("10 days");
		inpatientDao.updateInpatient(inpatient);
		inpatient = inpatientDao.searchInpatient(1,1);
		System.out.print("After update Inpatient is:"+inpatient);
	}

}
