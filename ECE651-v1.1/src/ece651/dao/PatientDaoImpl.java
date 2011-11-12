package ece651.dao;

import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ece651.dao.HibernateUtil;
import ece651.model.Patient;

public class PatientDaoImpl implements PatientDao {

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

	public PatientDaoImpl(){
		log.info(getClass().toString());
		this.session = HibernateUtil.getSessionFactory().openSession(); 
	}


	public Patient searchPatient(int patientId) throws DAOException {
		Patient patient;
		try{
			patient = (Patient)session.get(Patient.class, patientId);
		}catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return patient;
	}

	public void savePatient(Patient patient) throws DAOException {
		Transaction tran = null;
		try{
			tran = session.beginTransaction();
			tran.begin();
			session.save(patient);
			tran.commit();
		}catch (HibernateException e) {
			tran.rollback();
			throw new DAOException(e.getMessage());
		}
	}

	public void deletePatient(Patient patient) throws DAOException {
		Transaction tran = null;
		try{
			tran = session.beginTransaction();
			tran.begin();
			session.delete(patient);
			tran.commit();
		}catch (HibernateException e) {
			tran.rollback();
			throw new DAOException(e.getMessage());
		}
	}

	public void updatePatient(Patient patient) throws DAOException {
		Transaction tran = null;
		try{
			tran = session.beginTransaction();
			tran.begin();
			session.update(patient);
			tran.commit();
		}catch (HibernateException e) {
			tran.rollback();
			throw new DAOException(e.getMessage());
		}
	}

}
