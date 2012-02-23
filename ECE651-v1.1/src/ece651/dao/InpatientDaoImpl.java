package ece651.dao;

import org.apache.log4j.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ece651.dao.HibernateUtil;
import ece651.model.Inpatient;

public class InpatientDaoImpl implements InpatientDao {
	
	private static final Logger log = Logger.getLogger(InpatientDaoImpl.class);
	
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

	public InpatientDaoImpl() {
		log.info(getClass().toString());
		this.session = HibernateUtil.getSessionFactory().openSession(); 
	}

	@Override
	public void saveInpatient(Inpatient inpatient) throws DAOException {
		Transaction tran = null;
		try{
			tran = session.beginTransaction();
			tran.begin();
			session.save(inpatient);
			tran.commit();
		}catch (HibernateException e) {
			tran.rollback();
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public Inpatient searchInpatient(int inpatientId)
			throws DAOException {
		Inpatient inpatient;
		try{
			inpatient = (Inpatient)session.get(Inpatient.class, inpatientId);
		}catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return inpatient;
	}

	@Override
	public void updateInpatient(Inpatient inpatient) throws DAOException {
		Transaction tran = null;
		try{
			tran = session.beginTransaction();
			tran.begin();
			session.update(inpatient);
			tran.commit();
		}catch (HibernateException e) {
			tran.rollback();
			throw new DAOException(e.getMessage());
		}
	}

}
