package ece651.dao;

import org.hibernate.Session;

import ece651.model.Inpatient;

public interface InpatientDao {
	public Session getSession();
	public void cleanup();
	public void saveInpatient( Inpatient inpatient) throws DAOException;
	public Inpatient searchInpatient (int inpatientId, int visitationId) throws DAOException;
	public void updateInpatient( Inpatient inpatient) throws DAOException;
	//public void deleteSurgery( Surgery surgery) throws DAOException;
}
