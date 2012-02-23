package ece651.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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
	private List<SystemUser> retrieveSystemUsers;
	
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
		isSearchAll = searchAll.compareTo("View all users") == 0 ? true : false;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;	
	}
	
	public Map<String, Object> getSession() {
		return session;
	}
	
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;	
	}

	public Map<String, Object> getRequest() {
		return request;
	}
	
	public SystemUser getSystemUser() {
		return systemUser;
	}
	
	public void setRetrieveSystemUsers(List<SystemUser> systemUserList) {
		this.retrieveSystemUsers = systemUserList;
	}
	
	public List<SystemUser> getRetrieveSystemUsers() {
		return retrieveSystemUsers;
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
		boolean isOperationSucceed = true;
		System.out.println("CreateSystemUser is executed");//used for debug
		SystemUserDao systemUserDao = new SystemUserDaoImpl(); //initiate SystemUserDao instance
		request.put("Operation", "Create New System User:"+systemUser.getUsername());
		
		try
		{
			//if failed return String "fail"
			if((!systemUser.getUsername().trim().isEmpty()) && 
				(!systemUser.getPassword().trim().isEmpty()) &&
			    (!systemUser.getFirstName().trim().isEmpty()) &&
				(!systemUser.getLastName().trim().isEmpty()) && 
				(!systemUser.getGender().trim().isEmpty()) && 
				(!systemUser.getActive().trim().isEmpty()))
			{
				if (!systemUserBirthday.trim().isEmpty())
				{
					Date birthday =Date.valueOf(systemUserBirthday.trim());
					systemUser.setBirthday(birthday);		
				}
				systemUser.setActive("A");
				systemUserDao.saveUser(systemUser);
			}
			else
			{
				isOperationSucceed = false;
				this.setOperationStatus("Create New System User Failed: Please fill in the required fields indicated by '*'");
			}
		}
		catch (Exception e)
		{
			isOperationSucceed = false;
		    this.setOperationStatus("Create New System User Failed: Exception Happened: "+e.getMessage());
		}
		finally
		{
			systemUserDao.cleanup();
		}
		
		if (isOperationSucceed)
		  this.setOperationStatus("Create New System User Succeeded!");
		return isOperationSucceed ? SUCCESS : ERROR;
	}
	
	public String EditSystemUser(){
		boolean isOperationSucceed = true;
		System.out.println("EditSystemUser is executed");//used for debug
		SystemUserDao systemUserDao = new SystemUserDaoImpl(); //initiate SystemUserDao instance
		request.put("Operation", "Edit New System User:"+systemUser.getUsername());
		
		try
		{
			//if failed return String "fail"
			if((!systemUser.getUsername().trim().isEmpty()) && 
				(!systemUser.getPassword().trim().isEmpty()) &&
			    (!systemUser.getFirstName().trim().isEmpty()) &&
				(!systemUser.getLastName().trim().isEmpty()) && 
				(!systemUser.getGender().trim().isEmpty()) && 
				(!systemUser.getActive().trim().isEmpty()))
			{
				if (!systemUserBirthday.isEmpty())
				{
					Date birthday =Date.valueOf(systemUserBirthday);
					systemUser.setBirthday(birthday);		
				}
				systemUserDao.updateUser(systemUser);
			}
			else
			{
				isOperationSucceed = false;
				this.setOperationStatus("Update System User Failed: Please fill in the required fields indicated by '*'");
			}
		}
		catch (Exception e)
		{
			isOperationSucceed = false;
		    this.setOperationStatus("Update System User Failed: Exception Happened: "+e.getMessage());
		}
		finally
		{
			systemUserDao.cleanup();
		}
		
		//if success return String "success"
		if (isOperationSucceed)
			this.setOperationStatus("Update System User Succeeded!");
		restoreSystemUser(systemUser);
		return isOperationSucceed ? SUCCESS : ERROR;
	}
	
	private void restoreSystemUser(SystemUser systemUser)
	{
		retrieveSystemUsers = new ArrayList<SystemUser>();
		retrieveSystemUsers.add(systemUser);
	}
	
	public String SearchSystemUser(){
		retrieveSystemUsers = null;
		SystemUserDao systemUserDao = new SystemUserDaoImpl(); //initiate SystemUserDao instance
		
		try
		{
			if (isSearchAll)
				retrieveSystemUsers = systemUserDao.searchAllUser();
			else if (!searchContent.isEmpty())
			{
				SystemUser tempSysUser = null;
				
				if (searchType.compareTo("userName") == 0) //search by user name
					tempSysUser =  systemUserDao.searchUserByUsername(searchContent);
				else if (searchType.compareTo("userId") == 0) //search by user id
				{
					int userId = Integer.parseInt(searchContent);
					tempSysUser = systemUserDao.searchUserBySystemUserId(userId);
				}
				else
				{
					this.setOperationStatus("Software Error.");
					systemUserDao.cleanup();
					return ERROR;
				}
				
				if (tempSysUser != null)
				{
					retrieveSystemUsers = new ArrayList<SystemUser>();
					retrieveSystemUsers.add(tempSysUser);
				}
			}
			else
			{
				this.setOperationStatus("Invalid input.");
				systemUserDao.cleanup();
				return ERROR;
			}
			
			if (retrieveSystemUsers == null)
				this.setOperationStatus("No system users found!");
			else
				this.setOperationStatus("Success");
			
		}
		catch (Exception e)
		{
		    this.setOperationStatus("Create New System User Failed: Exception Happened: "+e.getMessage());
		    systemUserDao.cleanup();
			return ERROR;
		}

		systemUserDao.cleanup();
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