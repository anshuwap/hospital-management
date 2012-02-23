package ece651.action;

import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.struts2.interceptor.SessionAware;

import ece651.dao.DAOException;
import ece651.dao.SystemUserDao;
import ece651.dao.SystemUserDaoImpl;
import ece651.model.SystemUser;
import ece651.services.SystemUserService;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware{
	private static final Logger log = Logger.getLogger(LoginAction.class);
	private static final long serialVersionUID = 6066592355639005936L;
	private Map<String, Object> session;
	private SystemUser user;
	private String roleName;
	private String nextActionName;
	private String errorMessage;
	
	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}
	
	public String getRoleName() {
		return roleName.toUpperCase();
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
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

	public String login(){
		log.info("LoginAction is executed");
		log.info("Username:"+user.getUsername()+", password:"+user.getPassword());		
		SystemUserDao userdao = new SystemUserDaoImpl();
		SystemUser userdb;
		String Role="";
		//get user from Cache or database
		try {
			userdb = userdao.searchUserByUsername(user.getUsername());
		} catch (DAOException e) {
			errorMessage = "Database access error";		
			return ERROR;
		}
		catch(Exception ex){
			errorMessage = "System error";
			return ERROR;
		}
		//if not found
		if(userdb==null){
			errorMessage = "Username: " + user.getUsername() + " doesn't exist";
			return ERROR;
		}
		//if found
		log.info(userdb.getUsername() + " password in database:"+userdb.getPassword());
		//password error
		if (!user.getPassword().equals(userdb.getPassword())){
			errorMessage = "User name or password is not correct";	
			return ERROR;
		}
		//password is correct, check role
		if (userdb.getRoleType().equals("D")){
			this.nextActionName = "DOCTOR";
			Role = "Doctor";
		}else if (userdb.getRoleType().equals("N")){
			this.nextActionName = "NURSE";
			Role = "Nurse";
		}else if (userdb.getRoleType().equals("L")){
			this.nextActionName = "LAWYER";
			Role = "Lawyer";
		}else if (userdb.getRoleType().equals("I")){
			this.nextActionName = "ITGUY";
			Role = "ITGuy";
		}else{
			errorMessage = "User name role type is not correct";	
			return ERROR;
		}
		session.put("CurrentUser",userdb);
		session.put("Username",userdb.getUsername());
		session.put("Firstname", userdb.getFirstName());
		session.put("Lastname", userdb.getLastName());
		session.put("Role",Role);
		return SUCCESS;
		
	}

	public String loginByCache(){
		log.info("LoginAction is executed");
		log.info("Username:"+user.getUsername()+", password:"+user.getPassword());		
		SystemUserService userService = new SystemUserService();
		SystemUser userdb;
		String Role="";
		//get user from Cache or database
		try {
			userdb = userService.searchUserByUsername(user.getUsername());
		} catch (DAOException e) {
			errorMessage = "Database access error";		
			return ERROR;
		}
		catch(Exception ex){
			errorMessage = "System error";
			return ERROR;
		}
		//if not found
		if(userdb==null){
			errorMessage = "Username: " + user.getUsername() + " doesn't exist";
			return ERROR;
		}
		//if found
		log.info(userdb.getUsername() + " password in database:"+userdb.getPassword());
		//password error
		if (!user.getPassword().equals(userdb.getPassword())){
			errorMessage = "User name or password is not correct";	
			return ERROR;
		}
		//password is correct, check role
		if (userdb.getRoleType().equals("D")){
			this.nextActionName = "DOCTOR";
			Role = "Doctor";
		}else if (userdb.getRoleType().equals("N")){
			this.nextActionName = "NURSE";
			Role = "Nurse";
		}else if (userdb.getRoleType().equals("L")){
			this.nextActionName = "LAWYER";
			Role = "Lawyer";
		}else if (userdb.getRoleType().equals("I")){
			this.nextActionName = "ITGUY";
			Role = "ITGuy";
		}else{
			errorMessage = "User name role type is not correct";	
			return ERROR;
		}
		session.put("CurrentUser",userdb);
		session.put("Username",userdb.getUsername());
		session.put("Firstname", userdb.getFirstName());
		session.put("Lastname", userdb.getLastName());
		session.put("Role",Role);
		return SUCCESS;
	}	
	
	public String logout() throws Exception {
		log.info("LogoutAction is executed");
		//log.info("Username:" + user.getUsername() + ", password:" + user.getPassword());
		if (session.containsKey("CurrentUser")){
			session.remove("CurrentUser");
		}
		MDC.remove("USER_ID");
		return SUCCESS;		
	}
	
	public String BackToMainPage(){
		
		roleName = (String) session.get("Role");
		if (!roleName.isEmpty()){
		return SUCCESS;
		}
		else {
			return ERROR;
		}
	}
}
