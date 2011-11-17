package ece651.action;

import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import ece651.dao.DAOException;
import ece651.dao.SystemUserDao;
import ece651.dao.SystemUserDaoImpl;
import ece651.model.SystemUser;
import ece651.services.SystemUserService;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware{
	private Logger log = Logger.getLogger(getClass().toString());
	private static final long serialVersionUID = 6066592355639005936L;
	private Map<String, Object> session;
	private SystemUser user;
	private String nextActionName;
	private String errorMessage;
	private String result;
	
	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}	
	
	public LoginAction() {
		log.info("LoginAction is created");
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getNextActionName() {
		return nextActionName;
	}

	public void setNextActionName(String nextActionName) {
		this.nextActionName = nextActionName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String login(){
		log.info("LoginAction is executed");
		log.info("Username:"+user.getUsername()+", password:"+user.getPassword());		
		SystemUserDao userdao = new SystemUserDaoImpl();
		SystemUser userdb;
		String Role="";
		try {
			userdb = userdao.searchUserByUsername(user.getUsername());
			if(userdb==null){
				errorMessage = "Username: " + user.getUsername() + " doesn't exist";
				return ERROR;
			}
			log.info(userdb.getUsername() + " password in database:"+userdb.getPassword());
			if (user.getPassword().equals(userdb.getPassword())){
				if (userdb.getRoleType().equals("D")){
					this.nextActionName = "DOCTOR";
					Role = "Doctor";
				}	
				if (userdb.getRoleType().equals("N")){
					this.nextActionName = "NURSE";
					Role = "Nurse";
				}
				if (userdb.getRoleType().equals("L")){
					this.nextActionName = "LAWYER";
					Role = "Lawyer";
				}
				if (userdb.getRoleType().equals("I")){
					this.nextActionName = "ITGUY";
					Role = "ITGuy";
				}
				result = SUCCESS;
				session.put("CurrentUser",userdb);
				session.put("Username",userdb.getUsername());
				session.put("Firstname", userdb.getFirstName());
				session.put("Lastname", userdb.getLastName());
				session.put("Role",Role);
			}
			else {
				result = ERROR;
				errorMessage = "User name or password is not correct";				
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = ERROR;
			errorMessage = "Database access error";			
		}
		catch(Exception ex){
			ex.printStackTrace();
			result = ERROR;
			errorMessage = "System error";
		}
		return result;
	}

	public String loginByCache(){
		log.info("LoginAction is executed");
		log.info("Username:"+user.getUsername()+", password:"+user.getPassword());		
		String Role="";
		try {
			SystemUser userdb = SystemUserService.getInstance().searchUserByUsername(user.getUsername());
			if(userdb==null){
				errorMessage = "Username: " + user.getUsername() + " doesn't exist";
				return ERROR;
			}
			log.info(userdb.getUsername() + " password in database:"+userdb.getPassword());
			if (user.getPassword().equals(userdb.getPassword())){
				if (userdb.getRoleType().equals("D")){
					this.nextActionName = "DOCTOR";
					Role = "Doctor";
				}	
				if (userdb.getRoleType().equals("N")){
					this.nextActionName = "NURSE";
					Role = "Nurse";
				}
				if (userdb.getRoleType().equals("L")){
					this.nextActionName = "LAWYER";
					Role = "Lawyer";
				}
				if (userdb.getRoleType().equals("I")){
					this.nextActionName = "ITGUY";
					Role = "ITGuy";
				}
				result = SUCCESS;
				session.put("CurrentUser",userdb);
				session.put("Username",userdb.getUsername());
				session.put("Firstname", userdb.getFirstName());
				session.put("Lastname", userdb.getLastName());
				session.put("Role",Role);
			}
			else {
				result = ERROR;
				errorMessage = "User name or password is not correct";				
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = ERROR;
			errorMessage = "Database access error";			
		}
		catch(Exception ex){
			ex.printStackTrace();
			result = ERROR;
			errorMessage = "System error";
		}
		return result;
	}	
	
	public String logout() throws Exception {
		log.info("LogoutAction is executed");
		//log.info("Username:" + user.getUsername() + ", password:" + user.getPassword());
		if (session.containsKey("CurrentUser")){
			session.remove("CurrentUser");
		}
		return SUCCESS;		
	}
}
