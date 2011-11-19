package ece651.dao;

import org.hibernate.Session;

import ece651.model.Surgery;
import ece651.model.Visitation;

public interface SurgeryDao {
	public Session getSession();
	public void cleanup();
	public void saveSurgery( Surgery surgery) throws DAOException;
	//public Surgery searchSurgery (Visitation visitation)throws DAOException;
	public Surgery searchSurgery (int surgeryId) throws DAOException;
	public void updateSurgery( Surgery surgery) throws DAOException;
	//public void deleteSurgery( Surgery surgery) throws DAOException;
}
