package ece651.test.dao;

import java.sql.Date;
import junit.framework.TestCase;

import ece651.dao.DAOException;
import ece651.dao.PatientDaoImpl;
import ece651.dao.SystemUserDaoImpl;
import ece651.dao.SurgeryDaoImpl;
import ece651.model.Patient;
import ece651.model.Surgery;
import ece651.model.SystemUser;

public class TestSurgeryDao extends TestCase {
	
	public void  testsaveSurgery() throws DAOException{
		SurgeryDaoImpl surgeryDao = new SurgeryDaoImpl();
		PatientDaoImpl patientdao = new PatientDaoImpl();		
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		
		SystemUser doctor = userdao.searchUserBySystemUserId(2);
		SystemUser nurse = userdao.searchUserBySystemUserId(3);
		Patient patient = patientdao.searchPatient(1);
		
		Surgery surgery = new Surgery();
		surgery.setSurgeryId(1);
		surgery.setVisitationId(1);
		surgery.setPatient(patient);
		surgery.setIssueDoctor(doctor);
		surgery.setSurgetyDoctor(doctor);
		surgery.setNurse(nurse);
		
		Date sDate = Date.valueOf("2011-08-10");
		surgery.setSurgeryDate(sDate);
		surgery.setArrangementDescription("one hour");
		surgery.setSurgerySummary("a class");
		
		System.out.print("New Surgery is:"+surgery);
		surgeryDao.saveSurgery(surgery);
		System.out.print("After save() new Surgery is:"+surgery);
	}
	
	public void  testsearchSurgery() throws DAOException{
		SurgeryDaoImpl surgeryDao = new SurgeryDaoImpl();
		
		Surgery surgery = surgeryDao.searchSurgery(1,1);
		System.out.print("Surgery is:"+surgery);
	}

	public void  testupdateSurgery() throws DAOException{
		SurgeryDaoImpl surgeryDao = new SurgeryDaoImpl();
		
		Surgery surgery = surgeryDao.searchSurgery(1,1);
		System.out.print("Before update Surgery is:"+surgery);
		surgery.setSurgerySummary("very good");
		surgeryDao.updateSurgery(surgery);
		surgery = surgeryDao.searchSurgery(1,1);
		System.out.print("After update Surgery is:"+surgery);
	}

}
