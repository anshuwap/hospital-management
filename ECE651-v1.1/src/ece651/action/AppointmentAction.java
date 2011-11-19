package ece651.action;


import java.util.Map;
import java.sql.Date;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import ece651.dao.AppointmentDao;
import ece651.dao.AppointmentDaoImpl;
import ece651.dao.PatientDao;
import ece651.dao.PatientDaoImpl;
import ece651.dao.PrescriptionDao;
import ece651.dao.PrescriptionDaoImpl;
import ece651.dao.SystemUserDao;
import ece651.dao.SystemUserDaoImpl;
import ece651.model.Appointment;
import ece651.model.Patient;
import ece651.model.Prescription;
import ece651.model.SystemUser;
import ece651.model.Visitation;

public class AppointmentAction extends ActionSupport implements SessionAware,
		RequestAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private Appointment appointment;
	private SystemUser doctor;
	private SystemUser nurse;
	private String appointmentDate;
	private int doctorId;
	private String operationStatus;
	

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public SystemUser getDoctor() {
		return doctor;
	}

	public void setDoctor(SystemUser doctor) {
		this.doctor = doctor;
	}

	public SystemUser getNurse() {
		return nurse;
	}

	public void setNurse(SystemUser nurse) {
		this.nurse = nurse;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;	
	}
	
	public void setRequest(Map<String, Object> request) {
		this.request = request;
		
	}
	
	public String CreateAppointment(){
		System.out.println("Create Appointment is executed");
		Appointment newAppointment = new Appointment();
		AppointmentDao appointmentDao = new AppointmentDaoImpl();
		SystemUser tempSystemUser = new SystemUser();	
		try{
		newAppointment.setPatient((Patient)session.get("RetrievePatient"));
		newAppointment.setNurse((SystemUser)session.get("CurrentUser"));
		Object queryDoctor = QueryDoctor(doctorId);
		if(queryDoctor instanceof SystemUser ){
			newAppointment.setDoctor((SystemUser)queryDoctor);
		}
		else {
			throw new Exception();
		}
		newAppointment.setAppointmentDate(Date.valueOf(appointmentDate));
		newAppointment.setEndTime(appointment.getEndTime());
		newAppointment.setStartTime(appointment.getStartTime());
		newAppointment.setStatus(appointment.getStatus());			
	    appointmentDao.saveAppointment(newAppointment);	
		}catch(Exception e){
			this.setOperationStatus("Create Appointment Failed! Exception Happened:" + e.getMessage());
		    return ERROR;
		}	
		//just for test 
/*        
		newAppointment.setPatient((Patient)session.get("RetrievePatient"));
		newAppointment.setNurse((SystemUser)session.get("CurrentUser"));
		Object queryDoctor = QueryDoctor(doctorId);
		if(queryDoctor instanceof SystemUser ){
			newAppointment.setDoctor((SystemUser)queryDoctor);
		}
		else {
			throw new Exception();
		}		
		newAppointment.setAppointmentDate(Date.valueOf(appointmentDate));
		newAppointment.setEndTime(appointment.getEndTime());
		newAppointment.setStartTime(appointment.getStartTime());
		newAppointment.setStatus(appointment.getStatus());*/	
		//just for test
		
		this.session.put("CurrentAppointment",newAppointment);
		appointment = null;
		return SUCCESS;
	}
	
	public String EditAppointment(){
		return SUCCESS;
	}
	
	public String SearchAppointment(){
		return SUCCESS;
	}

	private Object QueryDoctor(int doctorId){
		SystemUser resultDoctor = null;	
		try{
			SystemUserDao systemUserDao = new SystemUserDaoImpl();
			resultDoctor = systemUserDao.searchUserBySystemUserId(doctorId);
			 if (resultDoctor == null){
				 //request.put("OperationStatus","Search Patient: Patient Not Exist");
				 return "Doctor Not Found";
			  }//end of if
			} catch (Exception e){
				//request.put("OperationStatus","Search Patient: Exception Happened" +e.getMessage());
				return "Exception Happened"+e.getMessage();
			}//end of catch
		
		return resultDoctor;
	}
	
	
}
