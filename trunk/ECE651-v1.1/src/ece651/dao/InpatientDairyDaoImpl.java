package ece651.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ece651.dao.HibernateUtil;
import ece651.model.Inpatient;
import ece651.model.InpatientDairy;
import ece651.model.InpatientDairyKey;

public class InpatientDairyDaoImpl implements InpatientDairyDao {
	Logger log = Logger.getLogger(getClass().toString());
	
	private Session session; 
	
	public Session getSession()
	{
		return this.session;
	}

	public void cleanup(){
		if (session != null) session.close();
		HibernateUtil.shutdown();
	}

	public InpatientDairyDaoImpl() {
		log.info(getClass().toString());
		this.session = HibernateUtil.getSessionFactory().openSession(); 
	}

	public void saveInpatientDairy(InpatientDairy inpatientDairy)
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

	@Override
	public ArrayList<InpatientDairy> searchInpatientDairy(Inpatient inpatient)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
}