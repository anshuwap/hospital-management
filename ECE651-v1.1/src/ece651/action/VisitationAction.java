package ece651.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class VisitationAction extends ActionSupport implements SessionAware, RequestAware{

	private Map<String, Object> session;
	private Map<String, Object> request;

	
	
	public void setRequest(Map<String, Object> request) {
		// TODO Auto-generated method stub
		
	}


	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		
	}

	//TODO create a visitation entry
	public String CreateVisitation(){
		return SUCCESS;
		
	}
	
	//TODO update a visitation entry
	public String EditVisitation(){
		return SUCCESS;
	}
	
	//TODO search visitation by patientID, then return a visitation instance
	public String ViewVisitation(){
		return SUCCESS;
		
	}
}
