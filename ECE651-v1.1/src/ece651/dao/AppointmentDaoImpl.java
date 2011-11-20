package ece651.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ece651.dao.HibernateUtil;
import ece651.model.Appointment;

public class AppointmentDaoImpl implements AppointmentDao {
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

	public AppointmentDaoImpl() {
		log.info(getClass().toString());
		this.session = HibernateUtil.getSessionFactory().openSession(); 
	}

	public Appointment searchAppointment(int appointmentId) throws DAOException {
		Appointment appointment;
		try{
			appointment = (Appointment)session.get(Appointment.class, appointmentId);
		}catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return appointment;
	}

	public void saveAppointment(Appointment appointment) throws DAOException {
		Transaction tran = null;
		try{
			tran = session.beginTransaction();
			tran.begin();
			session.save(appointment);
			tran.commit();
		}catch (HibernateException e) {
			tran.rollback();
			throw new DAOException(e.getMessage());
		}

	}

	public void updateAppointment(Appointment appointment) throws DAOException {
		Transaction tran = null;
		try{
			tran = session.beginTransaction();
			tran.begin();
			session.update(appointment);
			tran.commit();
		}catch (HibernateException e) {
			tran.rollback();
			throw new DAOException(e.getMessage());
		}
	}

	public List<Appointment> searchApptListBypId(int patiendId)throws DAOException {
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		try {
			Query hql = session.createQuery("from Appointment as app where app.patient.patientId=?");
			hql.setInteger(0, patiendId);
			appointmentList = hql.list();
		} catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return appointmentList;
	}

	public List<Appointment> searchApptListBydId(int doctorId)
			throws DAOException {
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		try {
			Query hql = session.createQuery("from Appointment as app where app.doctor.systemUserId=?");
			hql.setInteger(0, doctorId);
			appointmentList = hql.list();
		} catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return appointmentList;
	}

	public List<Appointment> searchAppListbyDidAndDate(int doctorId,
			String AppDate) throws DAOException {
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		try {
			SQLQuery sql = session.createSQLQuery("select * from Appointment where DoctorId=? and AppointmentDate=? and ( Status='A' or Status='V' ) order by AppointmentId desc").addEntity(Appointment.class);
			sql.setInteger(0, doctorId);
			sql.setString(1, AppDate);
			appointmentList = sql.list();
		} catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return appointmentList;
	}

	public List<Appointment> searchAppListbyDidandInOrder(int doctorId)
			throws DAOException {
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		try {
			SQLQuery sql = session.createSQLQuery("select * from Appointment where DoctorId=? order by AppointmentId desc").addEntity(Appointment.class);
			sql.setInteger(0, doctorId);
			appointmentList = sql.list();
		} catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return appointmentList;
	}

}
