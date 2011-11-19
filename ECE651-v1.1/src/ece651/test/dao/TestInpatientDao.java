package ece651.test.dao;

import java.sql.Date;
import junit.framework.TestCase;

import ece651.dao.DAOException;
import ece651.dao.PatientDaoImpl;
import ece651.dao.SystemUserDaoImpl;
import ece651.dao.InpatientDaoImpl;
import ece651.dao.VisitationDaoImpl;
import ece651.model.Patient;
import ece651.model.Inpatient;
import ece651.model.SystemUser;
import ece651.model.Visitation;

public class TestInpatientDao extends TestCase {
	
	public static Integer InpId;
	
	public void  testsaveInpatient() throws DAOException{
		InpatientDaoImpl inpatientDao = new InpatientDaoImpl();
		PatientDaoImpl patientdao = new PatientDaoImpl();		
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		VisitationDaoImpl visitationDao= new VisitationDaoImpl();
		
		SystemUser doctor = userdao.searchUserBySystemUserId(2);
		SystemUser nurse = userdao.searchUserBySystemUserId(3);
		Patient patient = patientdao.searchPatient(1);
		Visitation visit = visitationDao.searchVisitation(1);
		
		Inpatient inpatient = new Inpatient();
		inpatient.setVisitation(visit);
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
		InpId = inpatient.getInpatientId();
	}
	
	public void  testsearchInpatient() throws DAOException{
		InpatientDaoImpl inpatientDao = new InpatientDaoImpl();
		
		Inpatient inpatient = inpatientDao.searchInpatient(InpId);
		System.out.print("Inpatient is:"+inpatient);
	}
	
	public void  testsearchInpatient_default() throws DAOException{
		InpatientDaoImpl inpatientDao = new InpatientDaoImpl();
		
		Inpatient inpatient = inpatientDao.searchInpatient(1);
		System.out.print("Inpatient is:"+inpatient);
	}

	public void  testupdateInpatient() throws DAOException{
		InpatientDaoImpl inpatientDao = new InpatientDaoImpl();
		
		Inpatient inpatient = inpatientDao.searchInpatient(InpId);
		System.out.print("Before update Inpatient is:"+inpatient);
		inpatient.setArrangementDescription("10 days");
		inpatientDao.updateInpatient(inpatient);
		inpatient = inpatientDao.searchInpatient(InpId);
		System.out.print("After update Inpatient is:"+inpatient);
	}

}
