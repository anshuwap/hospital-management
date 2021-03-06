package ece651.dao;

import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ece651.dao.HibernateUtil;
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
}
