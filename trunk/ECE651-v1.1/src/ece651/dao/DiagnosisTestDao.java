package ece651.dao;

import org.hibernate.Session;

import ece651.model.DiagnosisTest;

public interface DiagnosisTestDao {
	public Session getSession();
	public void cleanup();
	public void saveDiagnosisTest(DiagnosisTest diagnosisTest) throws DAOException;
	public void saveDiagnosisTest_(DiagnosisTest diagnosisTest) throws DAOException;
	public DiagnosisTest searchDiagnosisTest (int diagnosisTestId, int visitationId) throws DAOException;
	//public ArrayList<DiagnosisTest> searchDiagnosisTest(Visitation visitation)throws DAOException;
	public void updateDiagnosisTest(DiagnosisTest diagnosisTest) throws DAOException;
	//public void deleteDiagnosisTest(DiagnosisTest diagnosisTest) throws DAOException;
}
