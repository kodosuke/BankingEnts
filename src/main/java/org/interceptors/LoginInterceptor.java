package org.interceptors;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor{

	@Override
	public void destroy() {
		
//		ServletActionContext.getRequest().getSession().invalidate();
		System.out.println("loginInterceptor destroyed.");
				
	}

	@Override
	public void init() {
		
		System.out.println("loginInterceptor initiated.");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		String ret = "login";
		Map<String, Object> sessionMap = invocation.getInvocationContext().getSession();
		
		if(!sessionMap.isEmpty() && sessionMap.get("signed") == "true") {
//			System.out.println("LOGIN_INTERCEPTOR CHECK#1");
			if(sessionMap.get("account") != null) {
//				System.out.println("LOGIN_INTERCEPTOR CHECK#2");
				ret = invocation.invoke();
				
			}
		}
		System.out.println("LOGIN_INTERCEPTOR returned " + ret);
		return ret ;
	}

	}

