package ece651.unittest.dao;

import java.sql.Date;
import junit.framework.TestCase;

import ece651.dao.DAOException;
import ece651.dao.PatientDaoImpl;
import ece651.dao.SystemUserDaoImpl;
import ece651.dao.SurgeryDaoImpl;
import ece651.dao.VisitationDaoImpl;
import ece651.model.Patient;
import ece651.model.Surgery;
import ece651.model.SystemUser;
import ece651.model.Visitation;

public class TestSurgeryDao extends TestCase {
	
	public static Integer SurId;
	
	public void  testsaveSurgery() throws DAOException{
		SurgeryDaoImpl surgeryDao = new SurgeryDaoImpl();
		PatientDaoImpl patientdao = new PatientDaoImpl();		
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		VisitationDaoImpl visitationDao= new VisitationDaoImpl();
		
		SystemUser doctor = userdao.searchUserBySystemUserId(2);
		SystemUser nurse = userdao.searchUserBySystemUserId(3);
		Patient patient = patientdao.searchPatient(1);
		Visitation visit = visitationDao.searchVisitation(1);
		
		Surgery surgery = new Surgery();
		surgery.setVisitation(visit);
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
		SurId = surgery.getSurgeryId();
	}
	
	public void  testsearchSurgery() throws DAOException{
		SurgeryDaoImpl surgeryDao = new SurgeryDaoImpl();
		
		Surgery surgery = surgeryDao.searchSurgery(SurId);
		System.out.print("Surgery is:"+surgery);
	}

	public void  testupdateSurgery() throws DAOException{
		SurgeryDaoImpl surgeryDao = new SurgeryDaoImpl();
		
		Surgery surgery = surgeryDao.searchSurgery(SurId);
		System.out.print("Before update Surgery is:"+surgery);
		surgery.setSurgerySummary("very good");
		surgeryDao.updateSurgery(surgery);
		surgery = surgeryDao.searchSurgery(SurId);
		System.out.print("After update Surgery is:"+surgery);
	}

}
