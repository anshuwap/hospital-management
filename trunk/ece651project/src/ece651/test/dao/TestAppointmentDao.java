package ece651.test.dao;

import java.sql.Date;
import junit.framework.TestCase;

import ece651.dao.DAOException;
import ece651.dao.AppointmentDaoImpl;
import ece651.model.Appointment;

public class TestAppointmentDao extends TestCase {
/*	
	public void  testsaveAppointment() throws DAOException{
		AppointmentDaoImpl appointmentdao = new AppointmentDaoImpl();
		
		Appointment appointment = new Appointment();
		appointment.setPatientId(2);
		appointment.setDoctorId(1);
		appointment.setNurseId(3);
		Date appDate = Date.valueOf("2011-03-10");
		appointment.setAppointmentDate(appDate);
		appointment.setStartTime("13:00");
		appointment.setEndTime("13:30");
		appointment.setStatus("A");
		
		System.out.print("New Appointment is:"+appointment);
		appointmentdao.saveAppointment(appointment);
		System.out.print("After save() new Appointment is:"+appointment);
	}
*/	
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
