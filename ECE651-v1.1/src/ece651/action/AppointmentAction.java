package ece651.action;


import java.util.ArrayList;
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
	private String currentAppID;
	private String doctorSelected;
	private String currentSystemUserID;
	private String currentPatientID;
	private String currentPatientHC;
	private String appointmentDate;
	private String startTime;
	private String endTime;
	private String operationStatus;
	private List<SystemUser> retrieveDoctors;
	private List<Appointment> retrieveAppointments;
	
	// search SystemUser Attributes Helper
	private String searchContent;
	private String searchType;
	private boolean isSearchAll;

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}
	
	public String getSearchContent() {
		return searchContent;
	}
	
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	public String getSearchType() {
		return searchType;
	}
	
	public void setSearchAll(String searchAll)
	{
		isSearchAll = searchAll.compareTo("View all appointments") == 0 ? true : false;
	}
	
	public boolean getIsEdit()
	{
		SystemUser tempUser = (SystemUser)(session.get("CurrentUser"));
		return tempUser.getRoleType().compareTo("N") == 0 ? true : false; //Only Nurse can edit appointment
	}
	
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
	
	public void setCurrentAppID (String appID) {
		this.currentAppID = appID;
	}
	
	public String getcurrentAppID () {
		return currentAppID;
	}
	
	public void setDoctorSelected (String doctorID) {
		this.doctorSelected = doctorID;
	}
	
	public String getDoctorSelected () {
		return doctorSelected;
	}
	
	public void setCurrentSystemUserID (String sysID) {
		this.currentSystemUserID = sysID;
	}
	
	public String getCurrentSystemUserID () {
		return ((SystemUser)(session.get("CurrentUser"))).getSystemUserId().toString();
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
	
	public void setRetrieveAppointments(List<Appointment> retrieveAppointments) {
		this.retrieveAppointments = retrieveAppointments;
	}
	
	public List<Appointment> getRetrieveAppointments() {
		return retrieveAppointments;
	}

	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}
	
	public String getOperationStatus() {
		return operationStatus;
	}
	
	// Only for Nurse
	public String InitCreateAction()
	{
		SystemUser currentNurse = (SystemUser)(session.get("CurrentUser"));
		
		if (currentNurse == null)
			return "BackToLogin";
		else
			currentSystemUserID = currentNurse.getSystemUserId().toString();
		
		Patient currentPatient = (Patient)(session.get("CurrentPatient"));

		if (currentPatient == null)
			return "BackToNurseMain";
		else
		{
			currentPatientID = currentPatient.getPatientId().toString();
			currentPatientHC = currentPatient.getHealthCardId();
		}
		
		return SUCCESS;
	}
	
	// Only for Nurse
	public String CreateAppointment(){
		boolean isOperationSucceed = true;
		System.out.println("CreateAppointment is executed");//used for debug
		AppointmentDao appointmentDao = new AppointmentDaoImpl(); //initiate AppointmentDao instance
		SystemUserDao systemUserDao = new SystemUserDaoImpl();
		PatientDao patientDao = new PatientDaoImpl();
		request.put("Operation", "Create New Apointment: (Doctor ID: " + doctorSelected + " Nurse ID: " + currentSystemUserID + " Patient ID: " + currentPatientID + ")");
		
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
				
			    SystemUser currentDoctor = systemUserDao.searchUserBySystemUserId(Integer.parseInt(doctorSelected));
			    if (currentDoctor == null)
			    	throw new Exception("Doctor is not found!");
			    
			    SystemUser currentNurse = systemUserDao.searchUserBySystemUserId(Integer.parseInt(currentSystemUserID));
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
		boolean isOperationSucceed = true;
		System.out.println("EditAppointment is executed");//used for debug
		AppointmentDao appointmentDao = new AppointmentDaoImpl(); //initiate AppointmentDao instance
		request.put("Operation", "Edit Apointment");
		
		try
		{
			Appointment tempApp = appointmentDao.searchAppointment(Integer.parseInt(currentAppID));
		    if (tempApp == null)
		    	throw new Exception("Appointment is not found!");
		    
			//if failed return String "fail"
			if((!appointmentDate.trim().isEmpty()) && 
				(!appointment.getStartTime().trim().isEmpty()) && 
				(!appointment.getEndTime().trim().isEmpty()) &&
				(!appointment.getStatus().trim().isEmpty()))
			{	
				Date date = Date.valueOf(appointmentDate.trim());
				tempApp.setAppointmentDate(date);
				tempApp.setStartTime(appointment.getStartTime().trim());
				tempApp.setEndTime(appointment.getEndTime().trim());
				tempApp.setStatus(appointment.getStatus().trim());
				appointmentDao.updateAppointment(tempApp);
				
			}
			else
			{
				isOperationSucceed = false;
				this.setOperationStatus("Update New Appointment Failed: Please fill in the required fields indicated by '*'");
			}
			
			restoreApp(tempApp);
			
		}
		catch (Exception e)
		{
			isOperationSucceed = false;
		    this.setOperationStatus("Update New Appointment Failed: Exception Happened: "+e.getMessage());
		}
		finally
		{
			appointmentDao.cleanup();
		}
		
		if (isOperationSucceed)
		  this.setOperationStatus("Update New Appointment Succeeded!");
		
		return isOperationSucceed ? SUCCESS : ERROR;
	}
	
	private void restoreApp(Appointment app)
	{
		retrieveAppointments = new ArrayList<Appointment>();
		retrieveAppointments.add(app);
	}
	
	public String SearchAppointment(){
		retrieveAppointments = null;
		AppointmentDao appointmentDao = new AppointmentDaoImpl(); //initiate SystemUserDao instance
		
		try
		{
			if (isSearchAll)
				retrieveAppointments = appointmentDao.searchApptList();
			else if (!searchContent.isEmpty())
			{
				int searchId = Integer.parseInt(searchContent);
				
				if (searchType.compareTo("appID") == 0) //search by appointment id
				{
					Appointment tempApp = appointmentDao.searchAppointment(searchId);
					if (tempApp != null)
					{
						retrieveAppointments = new ArrayList<Appointment>();
						retrieveAppointments.add(tempApp);
					}
				}
				else if (searchType.compareTo("docID") == 0) //search by doctor id
				{
					retrieveAppointments = appointmentDao.searchApptListBydId(searchId);
				}
				else if (searchType.compareTo("patID") == 0) //search by doctor id
				{
					retrieveAppointments = appointmentDao.searchApptListBypId(searchId);
				}
				else
				{
					this.setOperationStatus("Software Error.");
					appointmentDao.cleanup();
					return ERROR;
				}
				
			}
			else
			{
				this.setOperationStatus("Invalid input.");
				appointmentDao.cleanup();
				return ERROR;
			}
			
			if (retrieveAppointments == null || retrieveAppointments.size() == 0)
				this.setOperationStatus("No appointments found!");
			else
				this.setOperationStatus("Success");
		}
		catch (Exception e)
		{
		    this.setOperationStatus("Create New System User Failed: Exception Happened: "+e.getMessage());
		    appointmentDao.cleanup();
			return ERROR;
		}

		appointmentDao.cleanup();
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
