package ece651.dao;

import org.hibernate.Session;

import ece651.model.Surgery;

public interface SurgeryDao {
	public Session getSession();
	public void cleanup();
	public void saveSurgery( Surgery surgery) throws DAOException;
	public Surgery searchSurgery (int surgeryId) throws DAOException;
	public void updateSurgery( Surgery surgery) throws DAOException;
	//public void deleteSurgery( Surgery surgery) throws DAOException;
	//public Surgery searchSurgery (Visitation visitation)throws DAOException;
}
