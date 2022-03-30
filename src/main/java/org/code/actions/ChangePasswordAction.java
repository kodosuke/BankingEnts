package org.code.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.code.dao.CustomerDao;
import org.code.models.Customer;
import org.code.utils.PasswordSecurity;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class ChangePasswordAction extends ActionSupport implements SessionAware{
	
	String currentPassword;
	String newPassword;
	String confirmPassword;
	
	private SessionMap<String, Object> sessionMap;
	
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
		
		Customer customer = (Customer) sessionMap.get("CUSTOMER");

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");

		ArrayList<Object> list = new ArrayList<>();
		Word word = new Word();
		Gson gson = new Gson();

		String data;

		PrintWriter out = response.getWriter();
		if(customer.getPassword().equals(PasswordSecurity.generateHash(currentPassword)) && newPassword.equals(confirmPassword)) {
			customer.setPassword(PasswordSecurity.generateHash(newPassword));
			CustomerDao.updatePassword(customer);
			word.setMessage("Password updated.");
		} else { 	
			word.setError("Passwords don't match");
		}
		sessionMap.put("CUSTOMER", customer);
		
		list.add(word);

		data = gson.toJson(list);

		out.write(data);
		out.flush();
		out.close();
		return SUCCESS;
		
	}
	
}

