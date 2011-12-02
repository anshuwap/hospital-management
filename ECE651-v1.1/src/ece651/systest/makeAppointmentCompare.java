package ece651.systest;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

import ece651.dao.AppointmentDao;
import ece651.dao.AppointmentDaoImpl;
import ece651.dao.DAOException;
import ece651.model.Appointment;

public class makeAppointmentCompare extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Logger log = Logger.getLogger(makeAppointmentCompare.class);
		
		//search for appointment in Database
		
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		AppointmentDao appointmentdao = new AppointmentDaoImpl();
		
		try {
			appointmentList = appointmentdao.searchAppListbyDidAndDate(1, "2011-12-30");
		} catch (DAOException e) {
			e.printStackTrace();
		}finally{
			appointmentdao.cleanup();
		}
		
		Appointment appointment = null;
		if(appointmentList.size()>0){
			appointment = appointmentList.get(0);
		}
		
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
		
		//display report
		
		JFrame jFrame = new JFrame("System Test Case Report");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setBounds(200,200,500,300);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setAutoscrolls(true);
		textArea.setWrapStyleWord(true);
		Font font = new Font("Arial",Font.PLAIN,16);
		textArea.setFont(font);
		textArea.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

		jFrame.add(new JScrollPane(textArea), BorderLayout.CENTER);
		jFrame.setVisible(true);
	     
		if(makeAppReport.size()==0){
			log.info("System test case: makeAppointment completed successfully");
			textArea.append("System test case: makeAppointment completed successfully"+"\r\n"+"\r\n");
		}else{
			log.info("System test case: makeAppointment failed");
			textArea.append("System test case: makeAppointment failed"+"\r\n"+"\r\n");
			log.info("Errors:");
			textArea.append("Errors:"+"\r\n");
			for(String err: makeAppReport){
				log.info(err);
				textArea.append(err+"\r\n");
			}
		}

		//display
		jFrame.validate();
	}

}
