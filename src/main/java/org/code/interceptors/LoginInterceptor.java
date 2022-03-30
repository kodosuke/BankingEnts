package org.code.interceptors;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor extends ActionSupport implements Interceptor{


	@Override
	public void destroy() {
		
		System.out.println("\"loginInterceptor\" has been destroyed.");
	}

	@Override
	public void init() {
		
		System.out.println("\"loginInterceptor\" has been initialized.");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		String ret = LOGIN;
		Map<String, Object> sessionMap = invocation.getInvocationContext().getSession();
		
		if(!sessionMap.isEmpty()) {
			if(sessionMap.get("SIGN") != null && sessionMap.get("ACCOUNT") != null) {
				ret = invocation.invoke();
				
			} 
		}	
		System.out.println("\"LOGIN_INTERCEPTOR\" : "+  ret);
	
		return ret;
	}

}
