package org.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.beans.Account;
import org.dao.AccountDao;

import com.opensymphony.xwork2.ActionSupport;

public class DeleteAction extends ActionSupport implements SessionAware{
	
	private SessionMap<String, Object> sessionMap;
	
	public String delete() throws ClassNotFoundException, SQLException, IOException {
		Account account = (Account) sessionMap.get("account");
		if(account.getBalance() != 0) {
			addActionError("Account cannot be deleted since you have balance in the account.");
			return ERROR;
		}
		AccountDao.deleteAccount(account.getAccountNumber());
		sessionMap.clear();
		sessionMap.invalidate();
		addActionMessage("Account Deleted.");
		return SUCCESS;
	}

	
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;
		
	}

}
