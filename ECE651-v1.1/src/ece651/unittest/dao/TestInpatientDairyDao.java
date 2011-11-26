package ece651.unittest.dao;

import java.sql.Date;
import junit.framework.TestCase;

import ece651.dao.DAOException;
import ece651.dao.PatientDaoImpl;
import ece651.dao.SystemUserDaoImpl;
import ece651.dao.InpatientDairyDaoImpl;
import ece651.model.Patient;
import ece651.model.InpatientDairy;
import ece651.model.SystemUser;

public class TestInpatientDairyDao extends TestCase {
	
	public static Integer InpId;
	
	public void  testsaveInpatientDairy() throws DAOException{
		InpatientDairyDaoImpl inpatientDairyDao = new InpatientDairyDaoImpl();
		PatientDaoImpl patientdao = new PatientDaoImpl();		
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		
		SystemUser nurse = userdao.searchUserBySystemUserId(3);
		Patient patient = patientdao.searchPatient(1);
		
		InpatientDairy inpatientDairy = new InpatientDairy();
		inpatientDairy.setInpatientId(1);
		inpatientDairy.setPatient(patient);
		inpatientDairy.setNurse(nurse);
		
		//Date rDate = Date.valueOf("2011-08-10");
		inpatientDairy.setRecordDate(new Date(System.currentTimeMillis()));
		inpatientDairy.setDairyDescription("60% recovery");
		
		System.out.print("New InpatientDairy is:"+inpatientDairy);
		inpatientDairyDao.saveInpatientDairy(inpatientDairy);
		System.out.print("After save() new InpatientDairy is:"+inpatientDairy);
		InpId = inpatientDairy.getInpatientDairyId();
	}
	
	public void  testsearchInpatientDairy() throws DAOException{
		InpatientDairyDaoImpl inpatientDairyDao = new InpatientDairyDaoImpl();
		
		InpatientDairy inpatientDairy = inpatientDairyDao.searchInpatientDairy(InpId,1);
		System.out.print("InpatientDairy is:"+inpatientDairy);
	}

	public void  testupdateInpatientDairy() throws DAOException{
		InpatientDairyDaoImpl inpatientDairyDao = new InpatientDairyDaoImpl();
		
		InpatientDairy inpatientDairy = inpatientDairyDao.searchInpatientDairy(InpId,1);
		System.out.print("Before update InpatientDairy is:"+inpatientDairy);
		inpatientDairy.setDairyDescription("80% recovery");
		inpatientDairyDao.updateInpatientDairy(inpatientDairy);
		inpatientDairy = inpatientDairyDao.searchInpatientDairy(InpId,1);
		System.out.print("After update InpatientDairy is:"+inpatientDairy);
	}

}
