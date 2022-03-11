package org.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.beans.Account;
import org.beans.Transaction;
import org.dao.AccountDao;
import org.dao.TxnDao;
import org.utils.HashCode;
import org.utils.enums.Mode;

import com.opensymphony.xwork2.ActionSupport;

public class WithdrawAction extends ActionSupport implements SessionAware{
	
	private float amount;
	private SessionMap<String, Object> sessionMap;
	
	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;
		
	}

	public String withdraw() throws ClassNotFoundException, SQLException, IOException {
		Account account = (Account) sessionMap.get("account");
		System.out.println(amount);
		if(account.withdraw(amount)) {
			System.out.println("amount debited.");
			AccountDao.updateBalance(account);
			long txnHash = HashCode.generateHash(account.getAccountNumber(), amount, Mode.DEBIT);
			Transaction transaction = new Transaction(txnHash, account.getAccountNumber(), amount, Mode.DEBIT, new Date().getTime());
			TxnDao.insertTransaction(transaction);
			addActionMessage("Dear customer, your account has been debited with " + amount + ". Current balance : " + String.format("%.2f", account.getBalance()));
		} else {
		addActionError("Failed to debit the amount."); }
		return SUCCESS;
	}


}
