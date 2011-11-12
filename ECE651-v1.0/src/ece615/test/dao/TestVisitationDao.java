package ece615.test.dao;

import java.sql.Date;
import junit.framework.TestCase;

import ece651.dao.DAOException;
import ece651.dao.PatientDaoImpl;
import ece651.dao.SystemUserDaoImpl;
import ece651.dao.VisitationDaoImpl;
import ece651.model.Patient;
import ece651.model.SystemUser;
import ece651.model.Visitation;

public class TestVisitationDao extends TestCase {
	
	public void  testsaveVisitation() throws DAOException{
		VisitationDaoImpl visitationDao = new VisitationDaoImpl();
		PatientDaoImpl patientdao = new PatientDaoImpl();		
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		
		SystemUser doctor = userdao.searchUserBySystemUserId(2);
		Patient patient = patientdao.searchPatient(1);
		
		Visitation visitation = new Visitation();
		visitation.setVisitationId(2);
		visitation.setPatient(patient);
		visitation.setDoctor(doctor);
		visitation.setVisitationType("A");
		Date vDate = Date.valueOf("2011-08-10");
		visitation.setVisitationDate(vDate);
		visitation.setStartTime("13:00");
		visitation.setEndTime("13:30");
		visitation.setSymptomDescription("feel bad");
		visitation.setDiagnosisResult("good");
		
		System.out.print("New Visitation is:"+visitation);
		visitationDao.saveVisitation(visitation);
		System.out.print("After save() new visitation is:"+visitation);
	}
	
	public void  testsearchVisitation() throws DAOException{
		VisitationDaoImpl visitationdao = new VisitationDaoImpl();
		
		Visitation visitation = visitationdao.searchVisitation(1);
		System.out.print("Visitation is:"+visitation);
	}

	public void  testupdateVisitation() throws DAOException{
		VisitationDaoImpl visitationdao = new VisitationDaoImpl();
		
		Visitation visitation = visitationdao.searchVisitation(1);
		System.out.print("Before update Visitation is:"+visitation);
		visitation.setDiagnosisResult("healthy");
		visitationdao.updateVisitation(visitation);
		visitation = visitationdao.searchVisitation(1);
		System.out.print("After update Visitation is:"+visitation);
	}

}
