package ece615.test.dao;

import java.sql.Date;
import junit.framework.TestCase;

import ece651.dao.DAOException;
import ece651.dao.AppointmentDaoImpl;
import ece651.dao.PatientDaoImpl;
import ece651.dao.SystemUserDaoImpl;
import ece651.model.Appointment;
import ece651.model.Patient;
import ece651.model.SystemUser;

public class TestAppointmentDao extends TestCase {
	
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
		
		System.out.print("New Appointment is:"+appointment);
		appointmentdao.saveAppointment(appointment);
		System.out.print("After save() new Appointment is:"+appointment);
	}
	
	public void  testsearchAppointment() throws DAOException{
		AppointmentDaoImpl appointmentdao = new AppointmentDaoImpl();
		
		Appointment appointment = appointmentdao.searchAppointment(1);
		System.out.print("Appointment is:"+appointment);
	}

	public void  testupdateAppointment() throws DAOException{
		AppointmentDaoImpl appointmentdao = new AppointmentDaoImpl();
		
		Appointment appointment = appointmentdao.searchAppointment(1);
		System.out.print("Before update Appointment is:"+appointment);
		appointment.setStatus("V");
		appointmentdao.updateAppointment(appointment);
		appointment = appointmentdao.searchAppointment(1);
		System.out.print("After update Appointment is:"+appointment);
	}

}
