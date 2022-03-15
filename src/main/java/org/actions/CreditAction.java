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

public class CreditAction extends ActionSupport implements SessionAware {

	private SessionMap<String, Object> sessionMap;
	private float amount;
	
	String token;
	public void setToken(String token) { this.token = token; }
	public String getToken() { return token; }

	public String creditBalance() throws ClassNotFoundException, SQLException, IOException {

		Account account = (Account) sessionMap.get("account");
		Transaction txn;

		if (account.deposit(amount)) {
			AccountDao.updateBalance(account);
			txn = new Transaction(account.getAccountNumber(), amount, Mode.CREDIT, new Date().getTime());
			TxnDao.insertTransaction(txn);
			addActionMessage("Your account has been credited with the amount");
		} else {
			addActionError("The amount should be atleast 1 rupee.");
		}
		;
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
