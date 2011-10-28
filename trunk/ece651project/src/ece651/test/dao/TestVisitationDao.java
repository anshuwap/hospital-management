package ece651.test.dao;

import java.sql.Date;
import junit.framework.TestCase;

import ece651.dao.DAOException;
import ece651.dao.VisitationDaoImpl;
import ece651.model.Visitation;

public class TestVisitationDao extends TestCase {
	
	public void  testsearchAppointment() throws DAOException{
		VisitationDaoImpl visitationdao = new VisitationDaoImpl();
		
		Visitation visitation = visitationdao.searchVisitation(1);
		System.out.print("Visitation is:"+visitation);
	}

	public void  testupdateAppointment() throws DAOException{
		VisitationDaoImpl visitationdao = new VisitationDaoImpl();
		
		Visitation visitation = visitationdao.searchVisitation(1);
		System.out.print("Before update Visitation is:"+visitation);
		visitation.setDiagnosisResult("healthy");
		visitationdao.updateVisitation(visitation);
		visitation = visitationdao.searchVisitation(1);
		System.out.print("After update Visitation is:"+visitation);
	}

}
