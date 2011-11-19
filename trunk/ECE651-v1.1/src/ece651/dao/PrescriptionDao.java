package ece651.dao;

import org.hibernate.Session;

import ece651.model.Prescription;

public interface PrescriptionDao {
	public Session getSession();
	public void cleanup();
	

	public void savePrescription( Prescription prescription) throws DAOException;
	//public Prescription searchPrescription (Visitation visitation) throws DAOException;
	public Prescription searchPrescription (int prescriptionId) throws DAOException;
	public void updatePrescription( Prescription prescription) throws DAOException;
	//public void deletePrescription( Prescription prescription) throws DAOException;
}
