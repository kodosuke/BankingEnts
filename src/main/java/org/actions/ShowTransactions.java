package org.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.beans.Account;
import org.beans.Transaction;
import org.dao.TxnDao;

import com.opensymphony.xwork2.ActionSupport;

public class ShowTransactions extends ActionSupport implements SessionAware{
	
	private SessionMap<String, Object> sessionMap;
	
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;
	}
	
	public String view() throws ClassNotFoundException, SQLException, IOException {
		Account account = (Account) sessionMap.get("account");
		int accountNumber = account.getAccountNumber();
		HashMap<Long, Transaction> transactionsMap = TxnDao.getTransactionsByAccount(accountNumber);
		sessionMap.put("transactionsMap", transactionsMap);
		return SUCCESS;
	}
}
