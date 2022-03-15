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

public class TransferAction extends ActionSupport implements SessionAware {

	private SessionMap<String, Object> sessionMap;
	private float amount;
	private int recipient;
	private String recipientContact;

	String token;

	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
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

	public String transferBalance() throws ClassNotFoundException, SQLException, IOException {
		Account account = (Account) sessionMap.get("account");

		if (account.getContact().equals(recipientContact) || account.getAccountNumber() == recipient) {
			addActionError("Couldn't transfer to yourself.");
		} else {

			Account destination = AccountDao.getAccount(recipient, recipientContact);
			Transaction txn;

			if (destination.getCustomerName() != null) {

				if (account.withdraw(amount)) {
					AccountDao.updateBalance(account);
					txn = new Transaction(account.getAccountNumber(), amount, Mode.DEBIT, new Date().getTime());
					TxnDao.insertTransaction(txn);
					destination.deposit(amount);
					AccountDao.updateBalance(destination);
					txn = new Transaction(account.getAccountNumber(), amount, Mode.CREDIT, new Date().getTime());
					TxnDao.insertTransaction(txn);
					addActionMessage(
							"Your account has been debited with the amount for the transfer. Amount transferred successfully.");

				} else {
					addActionError("Insufficient balance.");
				}
			} else {
				addActionError("No account found with the details.");
			}
		}
		return SUCCESS;

	}
}