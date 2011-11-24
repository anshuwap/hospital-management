package ece651.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ece651.dao.HibernateUtil;
import ece651.model.InpatientDairy;
import ece651.model.InpatientDairyKey;

public class InpatientDairyDaoImpl implements InpatientDairyDao {
	
	private static final Logger log = Logger.getLogger(InpatientDairyDaoImpl.class);
	
	private Session session; 
	
	public Session getSession()
	{
		return this.session;
	}

	public void cleanup(){
		if (session != null) session.close();
		//HibernateUtil.shutdown();
	}

	public InpatientDairyDaoImpl() {
		log.info(getClass().toString());
		this.session = HibernateUtil.getSessionFactory().openSession(); 
	}

	public synchronized void saveInpatientDairy(InpatientDairy inpatientDairy)
			throws DAOException {
		
		int maxInpdId = 0;
		SQLQuery sql = (SQLQuery) session
			.createSQLQuery("select max(Inp.InpatientDairyId) from InpatientDairy as Inp where Inp.InpatientId=?");
		sql.setInteger(0, inpatientDairy.getInpatientId());
		if(null!=sql.list()&&null!=sql.list().get(0)){
			maxInpdId = (Integer) sql.list().get(0);
		}
		inpatientDairy.setInpatientDairyId(maxInpdId + 1);
		
		Transaction tran = null;
		try{
			tran = session.beginTransaction();
			tran.begin();
			session.save(inpatientDairy);
			tran.commit();
		}catch (HibernateException e) {
			tran.rollback();
			throw new DAOException(e.getMessage());
		}
	}

	public InpatientDairy searchInpatientDairy(int inpatientDairyId,
			int inpatientId) throws DAOException {
		InpatientDairy inpatientDairy;
		InpatientDairyKey inpatientDairyKey = new InpatientDairyKey();
		inpatientDairyKey.setInpatientDairyId(inpatientDairyId);
		inpatientDairyKey.setInpatientId(inpatientId);
		try{
			inpatientDairy = (InpatientDairy)session.get(InpatientDairy.class, inpatientDairyKey);
		}catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return inpatientDairy;
	}

	public void updateInpatientDairy(InpatientDairy inpatientDairy)
			throws DAOException {
		Transaction tran = null;
		try{
			tran = session.beginTransaction();
			tran.begin();
			session.update(inpatientDairy);
			tran.commit();
		}catch (HibernateException e) {
			tran.rollback();
			throw new DAOException(e.getMessage());
		}
	}

	public synchronized void saveInpatientDairy_(InpatientDairy inpatientDairy)
			throws DAOException {
		
		Connection conn = null;
		int newInpatientDairyId = 0;
		try {
			conn = session.connection();
			String sql = "select max(Inp.InpatientDairyId) from InpatientDairy as Inp where Inp.InpatientId=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inpatientDairy.getInpatientId());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				newInpatientDairyId = rs.getInt(1)+1;
				inpatientDairy.setInpatientDairyId(newInpatientDairyId);
			}
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
		
		Transaction tran = null;
		try{
			tran = session.beginTransaction();
			tran.begin();
			session.save(inpatientDairy);
			tran.commit();
		}catch (HibernateException e) {
			tran.rollback();
			throw new DAOException(e.getMessage());
		}
	}

}
