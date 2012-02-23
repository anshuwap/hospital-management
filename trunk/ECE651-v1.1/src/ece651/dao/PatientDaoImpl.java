package ece651.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ece651.dao.HibernateUtil;
import ece651.model.Patient;

public class PatientDaoImpl implements PatientDao {

	private static final Logger log = Logger.getLogger(PatientDaoImpl.class);
	
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

	public PatientDaoImpl(){
		log.info(getClass().toString());
		this.session = HibernateUtil.getSessionFactory().openSession(); 
	}


	@Override
	public Patient searchPatient(int patientId) throws DAOException {
		Patient patient;
		try{
			patient = (Patient)session.get(Patient.class, patientId);
		}catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return patient;
	}
	
	@Override
	public Patient searchPatientByHId(String healthCardId) throws DAOException {
		Patient patient = null;
		try{
			String hql ="select patient from Patient as patient where patient.healthCardId =?";
			Query q =session.createQuery(hql);
			q.setString(0, healthCardId);
			if( q.list().size()>0){
				patient = (Patient)q.list().get(0);
			}
		}catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return patient;
	}
	
	@Override
	public List<Patient> searchAllPatient() throws DAOException {
		List<Patient> patientList = new ArrayList<Patient>();
		try{
			Query q = session.createQuery("from Patient");
			patientList = q.list();
		}catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return patientList;
	}
	
	@Override
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

	@Override
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

	@Override
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

	@Override
	public Patient searchPatient(String healthCardID) throws DAOException {
		Patient patient = null;
		try{
			String sql ="select patient from Patient as patient where patient.healthCardId =?";
			Query q =session.createQuery(sql);
			q.setString(0,healthCardID);
			if( q.list().size()>0){
				patient = (Patient)q.list().get(0);
			}
		}catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return patient;
	}

}
