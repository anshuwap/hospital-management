package ece651.action;


import java.util.Map;
import java.sql.Date;
import java.sql.Time;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import ece651.dao.AppointmentDao;
import ece651.dao.AppointmentDaoImpl;
import ece651.dao.PatientDao;
import ece651.dao.PatientDaoImpl;
import ece651.dao.SystemUserDao;
import ece651.dao.SystemUserDaoImpl;
import ece651.model.Appointment;
import ece651.model.Patient;
import ece651.model.SystemUser;
import ece651.model.Visitation;

public class AppointmentAction extends ActionSupport implements SessionAware, RequestAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private Appointment appointment;
	private String doctorID;
	private String nurseID;
	private String patientID;
	private String appointmentDate;
	private String startTime;
	private String endTime;
	private String operationStatus;
	
	public void setSession(Map<String, Object> session) {
		this.session = session;	
	}
	
	public Map<String, Object> getSession() {
		return session;	
	}
	
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	
	public Map<String, Object> getRequest() {
		return request;
	}
	
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	
	public Appointment getAppointment() {
		return appointment;
	}
	
	public void setDoctorID (String doctorID) {
		this.doctorID = doctorID;
	}
	
	public String getDoctorID () {
		return doctorID;
	}
	
	public void setNurseID (String nurseID) {
		this.nurseID = nurseID;
	}
	
	public String getNurseID () {
		return nurseID;
	}
	
	public void setPatientID (String patientID) {
		this.patientID = patientID;
	}
	
	public String getPatientID () {
		return patientID;
	}
	
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	
	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getEndTime() {
		return endTime;
	}

	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}
	
	public String getOperationStatus() {
		return operationStatus;
	}
	
	public String Init()
	{
		return SUCCESS;
	}
	
	public String CreateAppointment(){
		boolean isOperationSucceed = true;
		System.out.println("CreateAppointment is executed");//used for debug
		AppointmentDao appointmentDao = new AppointmentDaoImpl(); //initiate AppointmentDao instance
		request.put("Operation", "Create New Apointment: (Doctor ID: " + appointment.getDoctor().getSystemUserId() + " Nurse ID: " + appointment.getNurse().getSystemUserId() + " Patient ID: " + appointment.getPatient().getPatientId() + ")");
		
		try
		{
			//if failed return String "fail"
			if((!doctorID.trim().isEmpty()) && 
				(!nurseID.trim().isEmpty()) &&
				(!patientID.trim().isEmpty()) && 
				(!appointmentDate.trim().isEmpty()) && 
				(!startTime.trim().isEmpty()) && 
				(!endTime.trim().isEmpty()))
			{
				Date date = Date.valueOf(appointmentDate.trim());
				appointment.setAppointmentDate(date);
				
				appointment.setStatus("A");
				appointmentDao.saveAppointment(appointment);
			}
			else
			{
				isOperationSucceed = false;
				this.setOperationStatus("Create New Appointment Failed: Please fill in the required fields indicated by '*'");
			}
		}
		catch (Exception e)
		{
			isOperationSucceed = false;
		    this.setOperationStatus("Create New Appointment Failed: Exception Happened: "+e.getMessage());
		}
		finally
		{
			appointmentDao.cleanup();
		}
		
		if (isOperationSucceed)
		  this.setOperationStatus("Create New Appointment Succeeded!");
		return isOperationSucceed ? SUCCESS : ERROR;
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
