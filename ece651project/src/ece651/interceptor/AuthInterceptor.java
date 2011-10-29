package ece651.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import ece651.model.SystemUser;

public class AuthInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// Map session = invocation.getInvocationContext().getSession();
		Map session = ActionContext.getContext().getSession();
		if((SystemUser) session.get("CurrentUser") == null) {
			System.out.println("CurrentUser didn't login, go to login page");
			return Action.LOGIN;  // return "login"/;
		}else{			
			String result = invocation.invoke();
			System.out.println("CurrentUser did login, then result="+result);
			return result;
		}		
	}
}
