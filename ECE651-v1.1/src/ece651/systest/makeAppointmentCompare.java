package ece651.systest;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import junit.framework.TestCase;

import ece651.dao.AppointmentDao;
import ece651.dao.AppointmentDaoImpl;
import ece651.dao.DAOException;
import ece651.model.Appointment;

public class makeAppointmentCompare extends TestCase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Logger log = Logger.getLogger(makeAppointmentCompare.class);
		
		//search for appointment in Database
		
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		AppointmentDao appointmentdao = new AppointmentDaoImpl();
		
		try {
			appointmentList = appointmentdao.searchAppListbyDidAndDate(2, "2011-11-30");
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			appointmentdao.cleanup();
		}
		Appointment appointment = appointmentList.get(0);
		
		List<String> makeAppReport = new ArrayList<String>();
		
		//compare
		
		if(null!=appointment){
			if(!"jzhang".equalsIgnoreCase(appointment.getDoctor().getUsername())){
				makeAppReport.add("Appointment doctor mis-match");
			}
			if(!"Bill Gates".equalsIgnoreCase(appointment.getPatient().getPatientName())){
				makeAppReport.add("Appointment patient mis-match");
			}
			if(!"chenli".equalsIgnoreCase(appointment.getNurse().getUsername())){
				makeAppReport.add("Appointment nurse mis-match");
			}
			if(!"08:30:00".equalsIgnoreCase(appointment.getStartTime())){
				makeAppReport.add("Appointment startTime mis-match");
			}
			if(!"12:00:00".equalsIgnoreCase(appointment.getEndTime())){
				makeAppReport.add("Appointment endTime mis-match");
			}
		}else{
			makeAppReport.add("Appointment is not found in Database");
		}
		
		//report
		
		if(makeAppReport.size()==0){
			log.info("System test case: makeAppointment succeed");
		}else{
			log.info("System test case: makeAppointment fail");
			log.info("System test case: makeAppointment report: ");
			
			for(String err: makeAppReport){
				log.info(err);
			}
		}
	}

}
