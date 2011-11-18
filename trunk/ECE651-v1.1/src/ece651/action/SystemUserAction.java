package ece651.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class SystemUserAction extends ActionSupport implements SessionAware,
		RequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map<String, Object> request;

	public void setSession(Map<String, Object> session) {
		this.session = session;	
	}
	
	public void setRequest(Map<String, Object> request) {
		this.request = request;	
	}
	
	public Map<String, Object> getSession() {
		return session;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public String CreateSystemUser(){
		return SUCCESS;
		
	}
	
	public String EditSystemUser(){
		return SUCCESS;
		
	}
	
	public String SearchystemUser(){
		return SUCCESS;
		
	}

}
