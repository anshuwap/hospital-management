package ece651.test.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import ece651.dao.DAOException;
import ece651.dao.AppointmentDaoImpl;
import ece651.dao.PatientDaoImpl;
import ece651.dao.SystemUserDaoImpl;
import ece651.model.Appointment;
import ece651.model.Patient;
import ece651.model.SystemUser;

public class TestAppointmentDao extends TestCase {
	
	public static Integer appId;
	
	public void  testsaveAppointment() throws DAOException{
		AppointmentDaoImpl appointmentdao = new AppointmentDaoImpl();
		PatientDaoImpl patientdao = new PatientDaoImpl();		
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		
		SystemUser doctor = userdao.searchUserBySystemUserId(2);
		SystemUser nurse = userdao.searchUserBySystemUserId(3);
		Patient patient = patientdao.searchPatient(1);
		
		Appointment appointment = new Appointment();
		appointment.setPatient(patient);
		appointment.setDoctor(doctor);
		appointment.setNurse(nurse);
		Date appDate = Date.valueOf("2011-08-10");
		appointment.setAppointmentDate(appDate);
		appointment.setStartTime("13:00");
		appointment.setEndTime("13:30");
		appointment.setStatus("A");
		
		appointmentdao.saveAppointment(appointment);
		appId = appointment.getAppointmentId();
		assertNotSame(0, appId);
	}
	
	public void  testsearchAppointment() throws DAOException{
		AppointmentDaoImpl appointmentdao = new AppointmentDaoImpl();
		
		Appointment appointment = appointmentdao.searchAppointment(appId);
		//assertEquals("13:00", appointment.getStartTime());
		assertEquals("Bill Gates", appointment.getPatient().getPatientName());
	}

	public void  testupdateAppointment() throws DAOException{
		AppointmentDaoImpl appointmentdao = new AppointmentDaoImpl();	
		
		Appointment appointment = appointmentdao.searchAppointment(appId);
		appointment.setStatus("V");
		appointmentdao.updateAppointment(appointment);
		Appointment appdb = appointmentdao.searchAppointment(appId);
		assertEquals("V", appdb.getStatus());
	}
	
	public void  testsearchApptListBypId() throws DAOException{
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		AppointmentDaoImpl appointmentdao = new AppointmentDaoImpl();
		
		appointmentList = appointmentdao.searchApptListBypId(1);
		Appointment appointment = appointmentList.get(0);
		assertEquals("Bill Gates", appointment.getPatient().getPatientName());
	}
	
	public void  testsearchApptListBydId() throws DAOException{
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		AppointmentDaoImpl appointmentdao = new AppointmentDaoImpl();
		
		appointmentList = appointmentdao.searchApptListBydId(2);
		Appointment appointment = appointmentList.get(0);
		assertEquals("wzheng", appointment.getDoctor().getUsername());
	}
	
	public void  testsearchAppListbyDidAndDate() throws DAOException{
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		AppointmentDaoImpl appointmentdao = new AppointmentDaoImpl();
		
		appointmentList = appointmentdao.searchAppListbyDidAndDate(2, "2011-08-10");
		Appointment appointment = appointmentList.get(0);
		assertEquals("wzheng", appointment.getDoctor().getUsername());
	}
	
	public void  testsearchAppListbyDidandInOrder() throws DAOException{
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		AppointmentDaoImpl appointmentdao = new AppointmentDaoImpl();
		
		appointmentList = appointmentdao.searchAppListbyDidandInOrder(2);
		Appointment appointment = appointmentList.get(0);
		assertEquals("wzheng", appointment.getDoctor().getUsername());
	}
}
