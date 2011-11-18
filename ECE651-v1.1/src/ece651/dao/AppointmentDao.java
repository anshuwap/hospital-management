package ece651.dao;

import java.util.List;

import ece651.model.Appointment;

public interface AppointmentDao {
	public void cleanup();
	public List<Appointment> searchApptListBydId(int doctorId) throws DAOException;
	public List<Appointment> searchApptListBypId(int patiendId)throws DAOException;
	public Appointment searchAppointment (int appointmentId ) throws DAOException;
	public void saveAppointment( Appointment appointment) throws DAOException;
	public void updateAppointment( Appointment appointment) throws DAOException;
}
