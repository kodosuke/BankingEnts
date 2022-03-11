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
import org.utils.HashCode;
import org.utils.enums.Mode;

import com.opensymphony.xwork2.ActionSupport;

public class TransferAction extends ActionSupport implements SessionAware{

	private int destNumber;
	private float amount;
	private SessionMap<String, Object> sessionMap;
	
	public int getDestNumber() {
		return destNumber;
	}
	public void setDestNumber(int destNumber) {
		this.destNumber = destNumber;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	public String transfer() throws ClassNotFoundException, SQLException, IOException {
		
		if(AccountDao.checkIfExists(destNumber)) {
			Account destination = AccountDao.getAccountByNumber(destNumber);
			Account account = (Account) sessionMap.get("account");
			
			if(account.getAccountNumber() == destNumber) {
				addActionError("Cannot  be same account.");
				return SUCCESS;
			}
			if(account.withdraw(amount)) {
				System.out.println("amount debited.");
				AccountDao.updateBalance(account);
				long txnHash = HashCode.generateHash(account.getAccountNumber(), amount, Mode.DEBIT);
				Transaction transaction = new Transaction(txnHash, account.getAccountNumber(), amount, Mode.DEBIT, new Date().getTime());
				TxnDao.insertTransaction(transaction);
				destination.setBalance( destination.getBalance() + amount);
                AccountDao.updateBalance(destination);
                txnHash = HashCode.generateHash(destNumber, amount, Mode.CREDIT);
                Transaction txn = new Transaction(txnHash, destNumber, amount, Mode.CREDIT, new Date().getTime());
                TxnDao.insertTransaction(txn);
				addActionMessage("Dear customer, your account has been debited with " + amount + ". Current balance : " + String.format("%.2f", account.getBalance()));
			} else {
			addActionError("Failed to debit the amount."); }
			} else {
			addActionError("No such account found.");
		}
		
		return SUCCESS;
		
	}
	
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;
		
	}
	
}
