package ece651.test.dao;

import java.sql.Date;
import junit.framework.TestCase;

import ece651.dao.DAOException;
import ece651.dao.PatientDaoImpl;
import ece651.dao.SystemUserDaoImpl;
import ece651.dao.PrescriptionDaoImpl;
import ece651.dao.VisitationDaoImpl;
import ece651.model.Patient;
import ece651.model.Prescription;
import ece651.model.SystemUser;
import ece651.model.Visitation;

public class TestPrescriptionDao extends TestCase {
	
	public static Integer PrescId;
	
	public void  testsavePrescription() throws DAOException{
		PrescriptionDaoImpl prescriptionDao = new PrescriptionDaoImpl();
		PatientDaoImpl patientdao = new PatientDaoImpl();		
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		VisitationDaoImpl visitationDao= new VisitationDaoImpl();
		
		SystemUser doctor = userdao.searchUserBySystemUserId(2);
		Patient patient = patientdao.searchPatient(1);
		Visitation visit = visitationDao.searchVisitation(1);
		
		Prescription prescription = new Prescription();
		prescription.setVisitation(visit);
		prescription.setPatient(patient);
		prescription.setDoctor(doctor);

		Date pDate = Date.valueOf("2011-08-10");
		prescription.setPrescriptionDate(pDate);
		prescription.setPrescriptionDescription("VB VC");
		
		System.out.print("New Prescription is:"+prescription);
		prescriptionDao.savePrescription(prescription);
		System.out.print("After save() new Prescription is:"+prescription);
		PrescId = prescription.getPrescriptionId();
	}
	
	public void  testsearchPrescription() throws DAOException{
		PrescriptionDaoImpl prescriptionDao = new PrescriptionDaoImpl();
		
		Prescription prescription = prescriptionDao.searchPrescription(PrescId);
		System.out.print("Prescription is:"+prescription);
	}

	public void  testupdatePrescription() throws DAOException{
		PrescriptionDaoImpl prescriptionDao = new PrescriptionDaoImpl();
		
		Prescription prescription = prescriptionDao.searchPrescription(PrescId);
		System.out.print("Before update Prescription is:"+prescription);
		prescription.setPrescriptionDescription("VD+VC+sun light");
		prescriptionDao.updatePrescription(prescription);
		prescription = prescriptionDao.searchPrescription(PrescId);
		System.out.print("After update Prescription is:"+prescription);
	}

}
