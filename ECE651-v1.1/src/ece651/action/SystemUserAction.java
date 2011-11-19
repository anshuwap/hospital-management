package ece651.action;

import java.sql.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import ece651.dao.*;
import ece651.model.*;

public class SystemUserAction extends ActionSupport implements SessionAware, RequestAware 
{

	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map<String, Object> request;
	private SystemUser systemUser;
	private String roleName;
	private String operationStatus;
	private String systemUserBirthday;

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
	
	public SystemUser getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}
	
	public String getSystemUserBirthday()
	{
		return systemUserBirthday;
	}
	
	public void setSystemUserBirthday(String userBirthday)
	{
		this.systemUserBirthday = userBirthday;
	}
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getOperationStatus() {
		return operationStatus;
	}
	
	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}
	
	public String CreateSystemUser()
	{
		System.out.println("CreateSystemUser is executed");//used for debug
		SystemUserDao systemUserDao = new SystemUserDaoImpl(); //initiate SystemUserDao instance
		request.put("Operation", "Create New System User:"+systemUser.getUsername());
		
		//if failed return String "fail"
		if((!systemUser.getUsername().trim().isEmpty()) && 
			(!systemUser.getPassword().trim().isEmpty()) &&
		    (!systemUser.getFirstName().trim().isEmpty()) &&
			(!systemUser.getLastName().trim().isEmpty()))
		{
		  try
		  {
			  if (!systemUserBirthday.isEmpty())
			  {
				  Date birthday =Date.valueOf(systemUserBirthday);
				  systemUser.setBirthday(birthday);		
			  }
			  systemUser.setGender(systemUser.getGender());
			  systemUser.setRoleType(systemUser.getRoleType());
			  systemUser.setPassword(systemUser.getPassword());
			  systemUser.setActive("A");
			  systemUserDao.saveUser(systemUser);
		  }
		  catch (Exception e)
		  {
			//String errorInfo =  + e.getCause().toString();
		    this.setOperationStatus("Create New System User Failed: Exception Happened: "+e.getMessage());
			return ERROR;
		  }
		}
		else
		{
			this.setOperationStatus("Create New System User Failed: Please fill in the required fields indicated by '*'");
			return ERROR;
		}
		
		//if success return String "success"
		this.setOperationStatus("Create New System User Succeeded!");
		return SUCCESS;
	}
	
	public String EditSystemUser(){
		return SUCCESS;
		
	}
	
	public String SearchystemUser(){
		return SUCCESS;
		
	}
	
	public String BackToMainPage()
	{	
		roleName = ((String) session.get("Role")).toUpperCase();
		if (!roleName.isEmpty())
			return SUCCESS;
		else
			return ERROR;
	}
}