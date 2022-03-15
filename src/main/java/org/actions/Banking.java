package org.actions;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class Banking extends ActionSupport implements SessionAware{
	
	private SessionMap<String, Object> sessionMap;
	
	public String credit() {
		sessionMap.put("action", "credit");
		return SUCCESS;
	}
	public String debit() {
		sessionMap.put("action", "debit");
		return SUCCESS;
	}
	public String transfer() {
		sessionMap.put("action", "transfer");
		return SUCCESS;
	}
	
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;		
	}
}
