package ece651.action;


import java.util.List;
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
	private String currentDoctorID;
	private String currentNurseID;
	private String currentPatientID;
	private String currentPatientHC;
//	private SystemUser currentDoctor;
//	private SystemUser currentNurse;
//	private Patient currentPatient;
	private String appointmentDate;
	private String startTime;
	private String endTime;
	private String operationStatus;
	private List<SystemUser> retrieveDoctors;
	
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
	
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate.trim().substring(0,10);
	}
	
	public String getAppointmentDate() {
		return appointmentDate;
	}
	
	public void setCurrentDoctorID (String doctorID) {
		this.currentDoctorID = doctorID;
	}
	
	public String getCurrentDoctorID () {
		return currentDoctorID;
	}
	
	public void setCurrentNurseID (String nurseID) {
		this.currentNurseID = nurseID;
	}
	
	public String getCurrentNurseID () {
		return currentNurseID;
	}
	
	public void setCurrentPatientID (String patientID) {
		this.currentPatientID = patientID;
	}
	
	public String getCurrentPatientID () {
		return currentPatientID;
	}
	
	public void setCurrentPatientHC (String PatientHC) {
		this.currentPatientHC = PatientHC;
	}
	
	public String getCurrentPatientHC () {
		return currentPatientHC;
	}
	
/*	public void setCurrentDoctor (SystemUser doctor) {
		this.currentDoctor = doctor;
	}
	
	public SystemUser getCurrentDoctor() {
		return currentDoctor;
	}
	
	public void setCurrentNurse (SystemUser nurse) {
		this.currentNurse = nurse;
	}
	
	public SystemUser getCurrentNurse() {
		return currentNurse;
	}
	
	public void setCurrentPatient (Patient patient) {
		this.currentPatient = patient;
	}
	
	public Patient getCurrentPatient() {
		return currentPatient;
	}*/

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
	
	public void setRetrieveDoctors(List<SystemUser> retrieveDoctors) {
		this.retrieveDoctors = retrieveDoctors;
	}
	
	public List<SystemUser> getRetrieveDoctors() {
		return retrieveDoctors;
	}

	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}
	
	public String getOperationStatus() {
		return operationStatus;
	}
	
	public String InitAction()
	{
		SystemUser currentNurse = (SystemUser)(session.get("CurrentUser"));
		
		if (currentNurse == null)
			return "BacktoLogin";
		else
			currentNurseID = currentNurse.getSystemUserId().toString();
		
		Patient currentPatient = (Patient)(session.get("CurrentPatient"));

		if (currentPatient == null)
			return "BacktoNurseMain";
		else
		{
			currentPatientID = currentPatient.getPatientId().toString();
			currentPatientHC = currentPatient.getHealthCardId();
		}
		
		return SUCCESS;
	}
	
	public String CreateAppointment(){
		boolean isOperationSucceed = true;
		System.out.println("CreateAppointment is executed");//used for debug
		AppointmentDao appointmentDao = new AppointmentDaoImpl(); //initiate AppointmentDao instance
		SystemUserDao systemUserDao = new SystemUserDaoImpl();
		PatientDao patientDao = new PatientDaoImpl();
		request.put("Operation", "Create New Apointment: (Doctor ID: " + currentDoctorID + " Nurse ID: " + currentNurseID + " Patient ID: " + currentPatientID + ")");
		
		try
		{
			//if failed return String "fail"
			if((!appointmentDate.trim().isEmpty()) && 
				(!appointment.getStartTime().trim().isEmpty()) && 
				(!appointment.getEndTime().trim().isEmpty()) &&
				(!appointment.getStatus().trim().isEmpty()))
			{
				Date date = Date.valueOf(appointmentDate.trim());
				appointment.setAppointmentDate(date);
				
			    SystemUser currentDoctor = systemUserDao.searchUserBySystemUserId(Integer.parseInt(currentDoctorID));
			    if (currentDoctor == null)
			    	throw new Exception("Doctor is not found!");
			    
			    SystemUser currentNurse = systemUserDao.searchUserBySystemUserId(Integer.parseInt(currentNurseID));
			    if (currentNurse == null)
			    	throw new Exception("Nurse is not found!");
			    
			    Patient currentPatient = patientDao.searchPatient(Integer.parseInt(currentPatientID));
			    if (currentPatient == null)
			    	throw new Exception("Patient is not found!");
			    
				appointment.setDoctor(currentDoctor);
				appointment.setNurse(currentNurse);
				appointment.setPatient(currentPatient);
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

	public String GetDoctors()
	{
		boolean isOperationSucceed = true;
		SystemUserDao doctorDao = new SystemUserDaoImpl(); //initiate SystemUserDao instance
		try
		{
			retrieveDoctors = doctorDao.SearchUserByRole("D");

			if (retrieveDoctors == null)
				this.setOperationStatus("No doctor found!");
			else
				this.setOperationStatus("Get doctor Success");
		} 
		catch (Exception e) 
		{
			isOperationSucceed = false;
		    this.setOperationStatus("Get doctors Failed: Exception Happened: "+e.getMessage());
		}

		doctorDao.cleanup();
		return isOperationSucceed ? SUCCESS : ERROR;
	}
}
