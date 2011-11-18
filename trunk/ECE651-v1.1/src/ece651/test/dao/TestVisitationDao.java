package ece651.test.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import ece651.dao.AppointmentDaoImpl;
import ece651.dao.DAOException;
import ece651.dao.PatientDaoImpl;
import ece651.dao.SystemUserDaoImpl;
import ece651.dao.VisitationDaoImpl;
import ece651.model.Appointment;
import ece651.model.Patient;
import ece651.model.SystemUser;
import ece651.model.Visitation;

public class TestVisitationDao extends TestCase {
	
	public static Integer visitId_with_App;
	public static Integer visitId_without_App;
	
	public void  testsaveVisitation_with_App() throws DAOException{
		VisitationDaoImpl visitationDao = new VisitationDaoImpl();
		AppointmentDaoImpl appointmentDao = new AppointmentDaoImpl();
		PatientDaoImpl patientdao = new PatientDaoImpl();		
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		
		Appointment appointment = appointmentDao.searchAppointment(1);
		SystemUser doctor = userdao.searchUserBySystemUserId(2);
		Patient patient = patientdao.searchPatient(1);
		
		Visitation visitation = new Visitation();
		
		visitation.setAppointment(appointment);
		visitation.setPatient(patient);
		visitation.setDoctor(doctor);
		visitation.setVisitationType("A");
		Date vDate = Date.valueOf("2011-08-10");
		visitation.setVisitationDate(vDate);
		visitation.setStartTime("13:00");
		visitation.setEndTime("13:30");
		visitation.setSymptomDescription("feel bad");
		visitation.setDiagnosisResult("good");
		
		visitationDao.saveVisitation(visitation);
		visitId_with_App = visitation.getVisitationId();
		
		Visitation visitdb = visitationDao.searchVisitation(visitId_with_App);
		assertEquals(visitId_with_App, visitdb.getVisitationId());

	}
	
	public void  testsaveVisitation_without_App() throws DAOException{
		VisitationDaoImpl visitationDao = new VisitationDaoImpl();
		PatientDaoImpl patientdao = new PatientDaoImpl();		
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		
		SystemUser doctor = userdao.searchUserBySystemUserId(2);
		Patient patient = patientdao.searchPatient(1);
		
		Visitation visitation = new Visitation();
		
		visitation.setPatient(patient);
		visitation.setDoctor(doctor);
		visitation.setVisitationType("A");
		Date vDate = Date.valueOf("2011-08-10");
		visitation.setVisitationDate(vDate);
		visitation.setStartTime("13:00");
		visitation.setEndTime("13:30");
		visitation.setSymptomDescription("feel bad");
		visitation.setDiagnosisResult("good");
		
		visitationDao.saveVisitation(visitation);
		visitId_without_App = visitation.getVisitationId();
		
		Visitation visitdb = visitationDao.searchVisitation(visitId_without_App);
		assertEquals(visitId_without_App, visitdb.getVisitationId());

	}

	public void  testsearchVisitation_default() throws DAOException{
		VisitationDaoImpl visitationdao = new VisitationDaoImpl();
		Integer visitId = 1;
		Visitation visitation = visitationdao.searchVisitation(visitId);
		assertEquals(visitId, visitation.getVisitationId());
	}
	
	public void  testsearchVisitation() throws DAOException{
		VisitationDaoImpl visitationdao = new VisitationDaoImpl();
		
		Visitation visitation = visitationdao.searchVisitation(visitId_with_App);
		assertEquals(visitId_with_App, visitation.getVisitationId());
	}

	public void  testupdateVisitation() throws DAOException{
		VisitationDaoImpl visitationdao = new VisitationDaoImpl();
		
		Visitation visitation = visitationdao.searchVisitation(visitId_with_App);
		visitation.setDiagnosisResult("healthy");
		visitationdao.updateVisitation(visitation);
		Visitation visitdb = visitationdao.searchVisitation(visitId_with_App);
		assertEquals("healthy", visitdb.getDiagnosisResult());
	}
	
	public void  testsearchApptListBypId() throws DAOException{
		List<Visitation> visitList = new ArrayList<Visitation>();
		VisitationDaoImpl visitationdao = new VisitationDaoImpl();
		
		visitList = visitationdao.searchVisitListBypId(1);
		Visitation visitation = visitList.get(0);
		assertEquals("Bill Gates", visitation.getPatient().getPatientName());
	}
}
