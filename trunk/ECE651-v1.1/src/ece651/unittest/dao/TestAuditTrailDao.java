package ece651.unittest.dao;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

import ece651.dao.AuditTrailDaoImpl;
import ece651.dao.DAOException;
import ece651.model.AuditTrail;

public class TestAuditTrailDao extends TestCase {

	public static Integer patientId = 1;

	public void testsearchAuditTrail() throws DAOException{
		AuditTrailDaoImpl auditTrailDao = new AuditTrailDaoImpl();
		
		AuditTrail auditTrail = auditTrailDao.searchAuditTrail(1);
		assertNotNull(auditTrail);
	}

	public void testsearchAllAuditTrail () throws DAOException{
		AuditTrailDaoImpl auditTrailDao = new AuditTrailDaoImpl();
		
		List<AuditTrail> auditTrailList = new ArrayList<AuditTrail>();
		
		auditTrailList = auditTrailDao.searchAllAuditTrail();
		assertNotSame(0, auditTrailList.size());
	}
	
	public void testsearchAuditTrailByPId () throws DAOException{
		AuditTrailDaoImpl auditTrailDao = new AuditTrailDaoImpl();
		
		List<AuditTrail> auditTrailList = new ArrayList<AuditTrail>();
		
		auditTrailList = auditTrailDao.searchAuditTrailByPId("Patient", patientId);
		assertNotSame(0, auditTrailList.size());
	}
}
