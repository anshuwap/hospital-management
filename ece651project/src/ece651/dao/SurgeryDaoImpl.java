package ece651.dao;

import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ece651.dao.HibernateUtil;
import ece651.model.Surgery;
import ece651.model.SurgeryKey;

public class SurgeryDaoImpl implements SurgeryDao {
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

	public SurgeryDaoImpl() {
		log.info(getClass().toString());
		this.session = HibernateUtil.getSessionFactory().openSession(); 
	}

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

	public Surgery searchSurgery(int surgeryId, int visitationId) throws DAOException {
		Surgery surgery;
		SurgeryKey surgeryKey = new SurgeryKey();
		surgeryKey.setVisitationId(visitationId);
		surgeryKey.setSurgeryId(surgeryId);
		try{
			surgery = (Surgery)session.get(Surgery.class, surgeryKey);
		}catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return surgery;
	}

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
