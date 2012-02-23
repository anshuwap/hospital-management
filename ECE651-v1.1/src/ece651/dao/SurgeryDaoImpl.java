package ece651.dao;

import org.apache.log4j.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ece651.dao.HibernateUtil;
import ece651.model.Surgery;

public class SurgeryDaoImpl implements SurgeryDao {
	
	private static final Logger log = Logger.getLogger(SurgeryDaoImpl.class);
	
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

	public SurgeryDaoImpl() {
		log.info(getClass().toString());
		this.session = HibernateUtil.getSessionFactory().openSession(); 
	}

	@Override
	public void saveSurgery(Surgery surgery) throws DAOException {
		Transaction tran = null;
		try{
			tran = session.beginTransaction();
			tran.begin();
			session.save(surgery);
			tran.commit();
		}catch (HibernateException e) {
			tran.rollback();
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public Surgery searchSurgery(int surgeryId) throws DAOException {
		Surgery surgery;
		try{
			surgery = (Surgery)session.get(Surgery.class, surgeryId);
		}catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return surgery;
	}

	@Override
	public void updateSurgery(Surgery surgery) throws DAOException {
		Transaction tran = null;
		try{
			tran = session.beginTransaction();
			tran.begin();
			session.update(surgery);
			tran.commit();
		}catch (HibernateException e) {
			tran.rollback();
			throw new DAOException(e.getMessage());
		}
	}

}
