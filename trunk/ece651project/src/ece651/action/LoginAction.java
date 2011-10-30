package ece651.action;

import com.opensymphony.xwork2.ActionSupport;

import ece651.vom.RoleType;
import ece651.vom.SystemUser;

public class LoginAction extends ActionSupport {
	private String username;
	private String password;
	private SystemUser systemUser;
	private RoleType roleType;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String execute(SystemUser systemUser) throws Exception {
		// add query logic here to verify user credential in database
		
		roleType = systemUser.getRoleType();
		switch(roleType){
		case DOCTOR : return "isDoctor"; 
		case NURSE : return "isNurse";
		case LAWYER : return "isLawyer";
		case ITGUY : return "isITGuy"; 
		default : return "error";
		}
	}

}
