package ece651.dao;

import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ece651.dao.HibernateUtil;
import ece651.model.DiagnosisTest;
import ece651.model.DiagnosisTestKey;

public class DiagnosisTestDaoImpl implements DiagnosisTestDao {
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

	public DiagnosisTestDaoImpl() {
		log.info(getClass().toString());
		this.session = HibernateUtil.getSessionFactory().openSession(); 
	}

	public void saveDiagnosisTest(DiagnosisTest diagnosisTest)
			throws DAOException {
		Transaction tran = null;
		try{
			tran = session.beginTransaction();
			tran.begin();
			session.save(diagnosisTest);
			tran.commit();
		}catch (HibernateException e) {
			tran.rollback();
			throw new DAOException(e.getMessage());
		}
	}

	public DiagnosisTest searchDiagnosisTest(int diagnosisTestId, int visitationId)
			throws DAOException {
		DiagnosisTest diagnosisTest;
		DiagnosisTestKey diagnosisTestKey = new DiagnosisTestKey();
		diagnosisTestKey.setVisitationId(visitationId);
		diagnosisTestKey.setDiagnosisTestId(diagnosisTestId);
		try{
			diagnosisTest = (DiagnosisTest)session.get(DiagnosisTest.class, diagnosisTestKey);
		}catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return diagnosisTest;
	}

	public void updateDiagnosisTest(DiagnosisTest diagnosisTest)
			throws DAOException {
		Transaction tran = null;
		try{
			tran = session.beginTransaction();
			tran.begin();
			session.update(diagnosisTest);
			tran.commit();
		}catch (HibernateException e) {
			tran.rollback();
			throw new DAOException(e.getMessage());
		}
	}
}
