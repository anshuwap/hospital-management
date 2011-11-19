package ece651.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ece651.dao.HibernateUtil;
import ece651.model.DiagnosisTest;
import ece651.model.DiagnosisTestKey;
import ece651.model.Visitation;

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

		Connection conn = null;
		int newDiagnosisTestId = 0;
		try {
			conn = session.connection();
			String sql = "select max(Diag.DiagnosisTestId) from DiagnosisTest as Diag where Diag.VisitationId=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, diagnosisTest.getVisitationId());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				newDiagnosisTestId = rs.getInt(1)+1;
				diagnosisTest.setDiagnosisTestId(newDiagnosisTestId);
			}
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
		
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

	@Override
	public ArrayList<DiagnosisTest> searchDiagnosisTest(Visitation visitation)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
}
