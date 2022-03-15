package org.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.beans.Account;
import org.beans.Transaction;
import org.dao.AccountDao;
import org.dao.TxnDao;
import org.enums.Mode;

import com.opensymphony.xwork2.ActionSupport;

public class DebitAction extends ActionSupport implements SessionAware {
	
	private SessionMap<String, Object> sessionMap;
	private float amount;
	String token;
	public void setToken(String token) { this.token = token; }
	public String getToken() { return token; }
	
	public String debitBalance() throws ClassNotFoundException, SQLException, IOException {
		
		Account account = (Account) sessionMap.get("account");
		Transaction txn;

		if(account.withdraw(amount)) {
			AccountDao.updateBalance(account);
			txn = new Transaction(account.getAccountNumber(), amount, Mode.DEBIT, new Date().getTime());
			TxnDao.insertTransaction(txn);
			addActionMessage("Your account has been debited with the amount");
		} else {
			addActionError("Insufficient balance.");
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
