package ece651.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ece651.dao.HibernateUtil;
import ece651.model.Visitation;

public class VisitationDaoImpl implements VisitationDao {
	
	private static final Logger log = Logger.getLogger(VisitationDaoImpl.class);
	
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

	public VisitationDaoImpl() {
		log.info(getClass().toString());
		this.session = HibernateUtil.getSessionFactory().openSession(); 
	}

	@Override
	public Visitation searchVisitation(int visitationId) throws DAOException {
		Visitation visitation;
		try{
			visitation = (Visitation)session.get(Visitation.class, visitationId);
		}catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return visitation;
	}

	@Override
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

	@Override
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
	
	@Override
	public List<Visitation> searchVisitListBypId(int patiendId)throws DAOException {
		List<Visitation> visitList = new ArrayList<Visitation>();
		try {
			Query hql = session.createQuery("from Visitation as visit where visit.patient.patientId=?");
			hql.setInteger(0, patiendId);
			visitList = hql.list();
		} catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return visitList;
	}


	@Override
	public Visitation searchVisitListByAppId(int appointmentId) throws DAOException{
	Visitation visit = null;
	try {
		Query hql = session.createQuery("from Visitation as visit where visit.appointment.appointmentId=?");
		hql.setInteger(0, appointmentId);
		if( hql.list().size()>0){
			visit = (Visitation)hql.list().get(0);
		}
	} catch (HibernateException e) {
		throw new DAOException(e.getMessage());
	}
	return visit;
	}

}
