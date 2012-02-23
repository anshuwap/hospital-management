package ece651.dao;

import org.apache.log4j.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ece651.dao.HibernateUtil;
import ece651.model.Prescription;

public class PrescriptionDaoImpl implements PrescriptionDao {
	
	private static final Logger log = Logger.getLogger(PrescriptionDaoImpl.class);
	
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

	public PrescriptionDaoImpl() {
		log.info(getClass().toString());
		this.session = HibernateUtil.getSessionFactory().openSession(); 
	}

	@Override
	public void savePrescription(Prescription prescription) throws DAOException {
		Transaction tran = null;
		try{
			tran = session.beginTransaction();
			tran.begin();
			session.save(prescription);
			tran.commit();
		}catch (HibernateException e) {
			tran.rollback();
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public Prescription searchPrescription(int prescriptionId)
			throws DAOException {
		Prescription prescription;
		try{
			prescription = (Prescription)session.get(Prescription.class, prescriptionId);
		}catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return prescription;
	}

	@Override
	public void updatePrescription(Prescription prescription)
			throws DAOException {
		Transaction tran = null;
		try{
			tran = session.beginTransaction();
			tran.begin();
			session.update(prescription);
			tran.commit();
		}catch (HibernateException e) {
			tran.rollback();
			throw new DAOException(e.getMessage());
		}
	}

}
