package ece651.dao;

import java.util.ArrayList;

import org.hibernate.Session;

import ece651.model.*;

public interface VisitationDao {
	public Session getSession();
	public void cleanup();
	public void saveVisitation( Visitation visitation) throws DAOException;
	public Visitation searchVisitation (int visitationId) throws DAOException;
	public ArrayList<Visitation> searchVisitation (Patient patient) throws DAOException;
	public void updateVisitation( Visitation visitation) throws DAOException;
	//public void deleteVisitation( Visitation visitation) throws DAOException;
}
