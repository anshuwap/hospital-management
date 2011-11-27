package ece651.dao;

import java.util.List;

import org.hibernate.Session;
import ece651.model.AuditTrail;

public interface AuditTrailDao {
	public Session getSession();
	public void cleanup();
	public AuditTrail searchAuditTrail(int auditId) throws DAOException;
	public List<AuditTrail> searchAllAuditTrail() throws DAOException;
	public List<AuditTrail> searchAuditTrailByPId(String tableName, int patientId) throws DAOException;
}
