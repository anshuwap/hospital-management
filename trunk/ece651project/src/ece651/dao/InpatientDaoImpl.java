package ece651.dao;

import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ece651.dao.HibernateUtil;
import ece651.model.Inpatient;
import ece651.model.InpatientKey;

public class InpatientDaoImpl implements InpatientDao {
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

	public InpatientDaoImpl() {
		log.info(getClass().toString());
		this.session = HibernateUtil.getSessionFactory().openSession(); 
	}

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

	public Inpatient searchInpatient(int inpatientId, int visitationId)
			throws DAOException {
		Inpatient inpatient;
		InpatientKey inpatientKey = new InpatientKey();
		inpatientKey.setVisitationId(visitationId);
		inpatientKey.setInpatientId(inpatientId);
		try{
			inpatient = (Inpatient)session.get(Inpatient.class, inpatientKey);
		}catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return inpatient;
	}

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
