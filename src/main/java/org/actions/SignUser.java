package org.actions;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.beans.Account;
import org.dao.AccountDao;
import org.utils.enums.Type;

import com.opensymphony.xwork2.ActionSupport;

public class SignUser extends ActionSupport implements SessionAware{

	private SessionMap<String, Object> sessionMap;
	
	private int accountNumber;
	private String customerName;
	private String password;
	private float balance;
	private Type accountType;
	private long createTime;
	
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
	
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;
		
	}
	
	public String loginUser() throws ClassNotFoundException, SQLException {
		
		String ret = LOGIN;
//		System.out.println(password);
		if(AccountDao.checkIfExists(accountNumber)) {
			
			Account account = AccountDao.getAccountByNumber(accountNumber);
//			System.out.println(account.getPassword());
			
			if(account.getPassword().equals(password)) {
				
				sessionMap.put("signed", "true");
				sessionMap.put("account", account);
				
				createTime = account.getCreateTime();
				customerName = account.getCustomerName();
				accountType = account.getAccountType();
				balance = account.getBalance();
				
				addActionMessage("Successfully logged in.");
				ret = SUCCESS;
				
			} else {
				addActionError("Invalid password.");
				sessionMap.clear();
			}
		} else {
			addActionError("No account found with the accountNumber " + accountNumber);
		}
		return ret;
	}
	
	public String logOut() {
		
		sessionMap.put("signed", "false");
		sessionMap.remove("account");
		sessionMap.invalidate();
		
		return SUCCESS;
	}
	
	@Override
	public void validate(){
		if("".equals(getAccountNumber())){
			addFieldError("accountNumber", "accountNumber can't be empty");
		}
		if("".equals(getPassword())){
			addFieldError("password", "Password can't be empty");
		}
	}

}
