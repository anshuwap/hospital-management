package ece651.dao;

import org.hibernate.Session;

import ece651.model.InpatientDairy;

public interface InpatientDairyDao {
	public Session getSession();
	public void cleanup();
	public void saveInpatientDairy( InpatientDairy inpatientDairy) throws DAOException;
	public InpatientDairy searchInpatientDairy (int inpatientDairyId, int inpatientId) throws DAOException;
	//public ArrayList<InpatientDairy> searchInpatientDairy (Inpatient inpatient) throws DAOException;
	public void updateInpatientDairy( InpatientDairy inpatientDairy) throws DAOException;
	//public void deleteInpatientDairy( Surgery surgery) throws DAOException;
}
