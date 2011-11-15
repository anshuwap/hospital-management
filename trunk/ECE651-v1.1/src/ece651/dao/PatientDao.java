package ece651.dao;

import ece651.model.Patient;

public interface PatientDao {
	public void cleanup();
	public void savePatient( Patient patient) throws DAOException;
	public Patient searchPatient (String healthCardID ) throws DAOException;
	public void updatePatient( Patient patient) throws DAOException;
	public void deletePatient( Patient patient) throws DAOException;
}
