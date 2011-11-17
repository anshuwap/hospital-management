package ece651.dao;

import java.util.ArrayList;

import ece651.model.Appointment;
import ece651.model.Patient;
import ece651.model.SystemUser;

public interface AppointmentDao {
	public void cleanup();
	public ArrayList<Appointment> searchApppintment(SystemUser doctor) throws DAOException;
	public Appointment searchAppointment (int appointmentId ) throws DAOException;
	public void saveAppointment( Appointment appointment) throws DAOException;
	public void updateAppointment( Appointment appointment) throws DAOException;
}
