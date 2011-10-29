package ece651.dao;

import ece651.model.Appointment;

public interface AppointmentDao {
	public void cleanup();
	public Appointment searchAppointment (int appointmentId ) throws DAOException;
	public void saveAppointment( Appointment appointment) throws DAOException;
	public void updateAppointment( Appointment appointment) throws DAOException;
}
