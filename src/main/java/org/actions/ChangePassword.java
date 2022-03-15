package org.actions;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.beans.Account;
import org.dao.AccountDao;
import org.utils.Secure;

import com.opensymphony.xwork2.ActionSupport;

public class ChangePassword extends ActionSupport implements SessionAware{
	
	String currentPassword;
	String newPassword;
	String confirmPassword;
	private SessionMap<String, Object> sessionMap;
	String token;
	public void setToken(String token) { this.token = token; }
	public String getToken() { return token; }
	
	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;
		
	}

	public String updatePassword() throws ClassNotFoundException, SQLException, IOException, NoSuchAlgorithmException {
		
		Account account = (Account) sessionMap.get("account");
		
		if(account.getPassword().equals(Secure.generateHash(currentPassword)) && newPassword.equals(confirmPassword)) {
			account.setPassword(Secure.generateHash(newPassword));
			AccountDao.updatePassword(account);
			addActionMessage("Password updated.");
		} else { 	
			addActionError("Passwords don't match");
		}
		
		return SUCCESS;
		
	}

}
