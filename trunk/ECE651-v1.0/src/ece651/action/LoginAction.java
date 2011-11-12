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
	private String returnCode;
	
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

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String login(){
		System.out.println("LoginAction is executed");
		System.out.println("loginId:"+user.getUsername()+", password:"+user.getPassword());		
		SystemUserDao userdao = new SystemUserDaoImpl();
		SystemUser userdb;
		try {
			userdb = userdao.searchUserByUsername(user.getUsername());
			if(userdb==null){
				returnCode = ERROR;
				errorMessage = "LoginId: " + user.getUsername() + " doesn't exist";
				return returnCode;
			}
			System.out.println("DB password:"+userdb.getPassword());
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
				returnCode = SUCCESS;
				session.put("CurrentUser",userdb);
				session.put("loginId",userdb.getUsername());	
				session.put("loginName",userdb.getFirstName()+" "+userdb.getLastName());	
			}
			else {
				returnCode = ERROR;
				errorMessage = "User name or password is not correct";				
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnCode = ERROR;
			errorMessage = "Database access error";			
		}
		catch(Exception ex){
			ex.printStackTrace();
			returnCode = ERROR;
			errorMessage = "System error";
		}
		return returnCode;
	}
	public String logout() throws Exception {
		System.out.println("LogoutAction is executed");
		//System.out.println("username:" + user.getLoginId() + ", password:" + user.getPassword());
		if (session.containsKey("CurrentUser")){
			session.remove("CurrentUser");
		}
		return SUCCESS;		
	}
}
