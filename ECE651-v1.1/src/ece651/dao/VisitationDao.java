package ece651.dao;

import java.util.List;

import org.hibernate.Session;

import ece651.model.*;

public interface VisitationDao {
	public Session getSession();
	public void cleanup();
	public void saveVisitation( Visitation visitation) throws DAOException;
	public void updateVisitation( Visitation visitation) throws DAOException;
	public Visitation searchVisitation (int visitationId) throws DAOException;
	public List<Visitation> searchVisitListBypId (int patientId) throws DAOException;	
	public Visitation searchVisitListByAppId (int appointmentId) throws DAOException;
}
