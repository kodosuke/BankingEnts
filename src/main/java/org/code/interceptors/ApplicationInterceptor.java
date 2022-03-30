package org.code.interceptors;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class ApplicationInterceptor extends ActionSupport implements Interceptor{

	@Override
	public void destroy() {
		
		System.out.println("\"applicationInterceptor\" has been destroyed.");
	}

	@Override
	public void init() {
		
		System.out.println("\"applicationInterceptor\" has been initialized.");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		String ret = ERROR;
		Map<String, Object> sessionMap = invocation.getInvocationContext().getSession();
		
		if(!sessionMap.isEmpty()) {
			if(sessionMap.get("SIGN") != null && sessionMap.get("ACCOUNT") != null) {
				ret = invocation.invoke();
				
			} 
		}	
		

		System.out.println("\"APPLICATION_INTERCEPTOR\" : "+  ret);
		
		if(ret.equals(ERROR)) {
			throw new Exception("Bad request.");
		}
		return ret;
	}
}
