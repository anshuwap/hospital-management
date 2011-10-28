package ece651.dao;

import org.hibernate.Session;

import ece651.model.Visitation;

public interface VisitationDao {
	public Session getSession();
	public void cleanup();
	public void saveVisitation( Visitation visitation) throws DAOException;
	public Visitation searchVisitation (int visitationId) throws DAOException;
	public void updateVisitation( Visitation visitation) throws DAOException;
	//public void deleteVisitation( Visitation visitation) throws DAOException;
}
