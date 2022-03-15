package org.actions;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.beans.Account;
import org.dao.AccountDao;
import org.utils.Secure;

import com.opensymphony.xwork2.ActionSupport;

public class LoginUser extends ActionSupport implements SessionAware{
	
	private String contact;
	private String password;
	
	private SessionMap<String, Object> sessionMap;
	
	
	
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		
		String ret = LOGIN;
		try {
			String pwdHash = Secure.generateHash(password);
			Account account = AccountDao.getAccountByNumber(contact, pwdHash );
			System.out.println(account.getAccountNumber());
			if(account.getAccountNumber() != 0) {
				sessionMap.put("signed", "true");
				sessionMap.put("account", account);
				ret = SUCCESS;
			} else {
				addActionError("Invalid credentials");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			ret = ERROR;
		}
		return ret;
	}
	
	public String logOut() {
		
		sessionMap.put("signed", "false");
		sessionMap.invalidate();
		addActionMessage("Successfully loggedOut.");
		return SUCCESS;
	}


	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;		
	}

}
