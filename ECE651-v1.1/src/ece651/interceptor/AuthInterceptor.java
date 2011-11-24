package ece651.interceptor;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

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
	private static final Logger log = Logger.getLogger(AuthInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
				
		// Map session = invocation.getInvocationContext().getSession();
		Map session = ActionContext.getContext().getSession();
		SystemUser user = (SystemUser) session.get("CurrentUser");
		
		if(user == null) {
			log.info("User didn't login, please go to login page");
			return Action.LOGIN;  
		}else{
			log.info(user.getUsername() + " authentication check ok");
			MDC.put("USER_ID", user.getUsername());
			String result = invocation.invoke();			
			return result;
		}		
	}
}
