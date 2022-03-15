package org.actions;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.beans.Account;
import org.dao.AccountDao;

import com.opensymphony.xwork2.ActionSupport;

public class TransferAction extends ActionSupport implements SessionAware {
	
	private SessionMap<String, Object> sessionMap;
	private float amount;
	private int recipient;
	private String recipientContact;
	
	
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


	public String getRecipientContact() {
		return recipientContact;
	}


	public void setRecipientContact(String recipientContact) {
		this.recipientContact = recipientContact;
	}


	public int getRecipient() {
		return recipient;
	}


	public void setRecipient(int recipient) {
		this.recipient = recipient;
	}
	
	public String transferBalance() throws ClassNotFoundException, SQLException {
		
		Account destination = AccountDao.getAccount(recipient, recipientContact);
		if(destination.getCustomerName() != null) {
			Account account = (Account) sessionMap.get("account");
			if(account.withdraw(amount)) {
				AccountDao.updateBalance(account);
				destination.deposit(amount);
				AccountDao.updateBalance(account);
				addActionMessage("Your account has been debited with the amount for the transfer. Amount transferred successfully.");
				
			} else {
				addActionError("Insufficient balance.");
			}
		}else {
			addActionError("No account found with the details.");
		}
		
		return SUCCESS;
		
	}
}