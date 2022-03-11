package org.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.beans.Account;
import org.dao.AccountDao;
import org.utils.enums.Type;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterUser extends ActionSupport implements SessionAware{

	private SessionMap<String, Object> sessionMap;
	
	private int accountNumber;
	private String customerName;
	private String password;
	private float balance;
	private Type accountType;
	long createTime;
	
	public int getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public float getBalance() {
		return balance;
	}


	public void setBalance(float balance) {
		this.balance = balance;
	}


	public Type getAccountType() {
		return accountType;
	}


	public void setAccountType(Type accountType) {
		this.accountType = accountType;
	}


	public long getCreateTime() {
		return createTime;
	}


	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}


	public String registerUser() throws ClassNotFoundException, SQLException, IOException {
		
		balance = accountType.balance;
		setCreateTime(new Date().getTime());
		Account account = new Account(customerName, balance, password, createTime, accountType);
		int accountNumber = AccountDao.insertAccount(account);
		addActionMessage("Dear Customer, your account has been created successfully with accountNumber "+ accountNumber + " and balance " + balance +" USD.");
		return SUCCESS;
	}
	
	
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;
		
	}

}
