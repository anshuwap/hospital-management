package ece651.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import ece651.dao.HibernateUtil;
import ece651.model.AuditTrail;

public class AuditTrailDaoImpl implements AuditTrailDao {

	private static final Logger log = Logger.getLogger(AuditTrailDaoImpl.class);
	
	private Session session; 
	
	@Override
	public Session getSession()
	{
		return this.session;
	}

	@Override
	public void cleanup(){
		if (session != null) session.close();
		//HibernateUtil.shutdown();
	}

	public AuditTrailDaoImpl(){
		log.info(getClass().toString());
		this.session = HibernateUtil.getSessionFactory().openSession(); 
	}

	@Override
	public AuditTrail searchAuditTrail(int auditId) throws DAOException {
		AuditTrail auditTrail;
		try{
			auditTrail = (AuditTrail)session.get(AuditTrail.class, auditId);
		}catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return auditTrail;
	}

	@Override
	public List<AuditTrail> searchAllAuditTrail() throws DAOException {
		List<AuditTrail> auditTrailList = new ArrayList<AuditTrail>();
		try{
			Query q = session.createQuery("from AuditTrail");
			auditTrailList = q.list();
		}catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return auditTrailList;
	}

	@Override
	public List<AuditTrail> searchAuditTrailByPId(String tableName, int patientId) throws DAOException {
		List<AuditTrail> auditTrailList = new ArrayList<AuditTrail>();
		try{
			Query q = session.createQuery("select audit from AuditTrail as audit where audit.tableName=? and audit.tableKey=?");
			q.setString(0, tableName);
			q.setInteger(1, patientId);
			auditTrailList = q.list();
		}catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return auditTrailList;
	}

}
