package ece651.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import ece651.dao.DAOException;
import ece651.dao.SystemUserDao;
import ece651.dao.SystemUserDaoImpl;
import ece651.model.SystemUser;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware{
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
		System.out.println("LoginAction is created");
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
		System.out.println("LoginAction is executed");
		System.out.println("Username:"+user.getUsername()+", password:"+user.getPassword());		
		SystemUserDao userdao = new SystemUserDaoImpl();
		SystemUser userdb;
		try {
			userdb = userdao.searchUserByUsername(user.getUsername());
			if(userdb==null){
				errorMessage = "Username: " + user.getUsername() + " doesn't exist";
				return ERROR;
			}
			System.out.println(userdb.getUsername() + " password in database:"+userdb.getPassword());
			if (user.getPassword().equals(userdb.getPassword())){
				if (userdb.getRoleType().equals("D")){
					this.nextActionName = "DOCTOR";
				}
				if (userdb.getRoleType().equals("S")){
					this.nextActionName = "STAFF";
				}
				if (userdb.getRoleType().equals("A")){
					this.nextActionName = "ADMIN";
				}
				if (userdb.getRoleType().equals("F")){
					this.nextActionName = "FINANCIAL";
				}
				if (userdb.getRoleType().equals("L")){
					this.nextActionName = "LEGAL";
				}	
				result = SUCCESS;
				session.put("CurrentUser",userdb);
				session.put("Username",userdb.getUsername());
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
		System.out.println("LogoutAction is executed");
		//System.out.println("Username:" + user.getUsername() + ", password:" + user.getPassword());
		if (session.containsKey("CurrentUser")){
			session.remove("CurrentUser");
		}
		return SUCCESS;		
	}
}
