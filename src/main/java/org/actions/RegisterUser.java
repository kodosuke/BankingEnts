package org.actions;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.beans.Account;
import org.dao.AccountDao;
import org.enums.Gender;
import org.enums.Type;
import org.utils.Secure;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterUser extends ActionSupport implements SessionAware{
	
	private int accountNumber;
	private String customerName;
	private String contact;
	private String dateOfBirth;
	private Gender gender;
	private String password;
	private Type accountType;
	private float balance;
	private long creationTime;
	String token;
	public void setToken(String token) { this.token = token; }
	public String getToken() { return token; }
	
	private SessionMap<String, Object> sessionMap;

	//
	private String cPassword;
	
	public String getcPassword() {
		return cPassword;
	}
	public void setcPassword(String cPassword) {
		this.cPassword = cPassword;
	}
	//	
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
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Type getAccountType() {
		return accountType;
	}
	public void setAccountType(Type accountType) {
		this.accountType = accountType;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public long getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}
	
	public String register() throws NoSuchAlgorithmException{
		
		String pwdHash = Secure.generateHash(password);
		System.out.println(pwdHash);
		
		try {
			if( !AccountDao.checkIfAvailable(contact)) {
				Account account = new Account(customerName, contact, dateOfBirth , gender, pwdHash, accountType, accountType.balance, new Date().getTime());
				int accountNumber = AccountDao.insertAccount(account);
				account.setAccountNumber(accountNumber);
				sessionMap.put("signed", "true");
				sessionMap.put("account", account);
				addActionMessage("Account successfully registered.");
				System.out.println("SUCCESS");
				return SUCCESS;
			}
			else {
				addActionMessage("An account with this e mail already available. Try login.");
				System.out.println("LOGIN");
				return LOGIN;
			}
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR");
			return ERROR;
		}
	}
	
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;		
	}

	
}
