package ece651.dao;

import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ece651.dao.HibernateUtil;
import ece651.model.Visitation;

public class VisitationDaoImpl implements VisitationDao {
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

	public VisitationDaoImpl() {
		log.info(getClass().toString());
		this.session = HibernateUtil.getSessionFactory().openSession(); 
	}

	public Visitation searchVisitation(int visitationId) throws DAOException {
		Visitation visitation;
		try{
			visitation = (Visitation)session.get(Visitation.class, visitationId);
		}catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return visitation;
	}

	public void saveVisitation(Visitation visitation) throws DAOException {
		Transaction tran = null;
		try{
			tran = session.beginTransaction();
			tran.begin();
			session.save(visitation);
			tran.commit();
		}catch (HibernateException e) {
			tran.rollback();
			throw new DAOException(e.getMessage());
		}
	}

	public void updateVisitation(Visitation visitation) throws DAOException {
		Transaction tran = null;
		try{
			tran = session.beginTransaction();
			tran.begin();
			session.update(visitation);
			tran.commit();
		}catch (HibernateException e) {
			tran.rollback();
			throw new DAOException(e.getMessage());
		}
	}
}
