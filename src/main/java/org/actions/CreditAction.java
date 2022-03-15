package org.actions;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.beans.Account;
import org.dao.AccountDao;

import com.opensymphony.xwork2.ActionSupport;

public class CreditAction extends ActionSupport implements SessionAware{
	
	private SessionMap<String, Object> sessionMap;
	private float amount;
	
	
	public String creditBalance() throws ClassNotFoundException, SQLException {
		
		Account account = (Account) sessionMap.get("account");
		if(account.deposit(amount)) {
			AccountDao.updateBalance(account);
			addActionMessage("Your account has been credited with the amount");
		} else {
			addActionError("The amount should be atleast 1 rupee.");
		};
		return SUCCESS;
	}
	
	
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;		
	}


	public float getAmount() {
		return amount;
	}


	public void setAmount(float amount) {
		this.amount = amount;
	}
}
