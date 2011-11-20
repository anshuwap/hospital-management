package ece651.dao;

import java.util.List;

import org.hibernate.Session;

import ece651.model.Patient;

public interface PatientDao {
	public Session getSession();
	public void cleanup();
	public Patient searchPatient(int patientId) throws DAOException;
	public Patient searchPatientByHId(String healthCardId) throws DAOException;
	public List<Patient> searchAllPatient() throws DAOException;
	public void savePatient( Patient patient) throws DAOException;
	public void updatePatient( Patient patient) throws DAOException;
	public void deletePatient( Patient patient) throws DAOException;
	public Patient searchPatient(String healthCardID) throws DAOException;
}
