package org.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.beans.Account;
import org.dao.AccountDao;

import com.opensymphony.xwork2.ActionSupport;

public class ChangePassword extends ActionSupport implements SessionAware{
	
	String cPassword;
	String nPassword;
	String cnPassword;
	private SessionMap<String, Object> sessionMap;
	
	public String getcPassword() {
		return cPassword;
	}
	public void setcPassword(String cPassword) {
		this.cPassword = cPassword;
	}
	public String getnPassword() {
		return nPassword;
	}
	public void setnPassword(String nPassword) {
		this.nPassword = nPassword;
	}
	public String getCnPassword() {
		return cnPassword;
	}
	public void setCnPassword(String cnPassword) {
		this.cnPassword = cnPassword;
	}
	
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;
		
	}

	public String updatePassword() throws ClassNotFoundException, SQLException, IOException {
		
		Account account = (Account) sessionMap.get("account");
		
		if(account.getPassword().equals(cPassword) && nPassword.equals(cnPassword)) {
			account.setPassword(nPassword);
			AccountDao.updatePassword(account);
			addActionMessage("Password updated.");
		} else {
			addActionError("Passwords don't match");
		}
		
		return SUCCESS;
		
	}

}
