package org.code.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.code.dao.AccountDao;
import org.code.dao.CustomerDao;
import org.code.enums.AccountType;
import org.code.enums.Gender;
import org.code.models.Account;
import org.code.models.Customer;
import org.code.utils.PasswordSecurity;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class SignUser extends ActionSupport implements SessionAware {

	private SessionMap<String, Object> sessionMap;

	private String customerName;
	private String contact;
	private String dateOfBirth;
	private String password;
	private Gender gender;
	private AccountType accountType;

	private String data;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String loginUser() throws IOException, ClassNotFoundException, SQLException, NoSuchAlgorithmException {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");

		PrintWriter out = response.getWriter();

		ArrayList<Object> list = new ArrayList<>();
		Word word = new Word();
		Gson gson = new Gson();
		
		if(CustomerDao.checkIfPresent(contact) == 0) {
			word.error = "No account found.";
			list.add(word);
		} else {
		
		String pwdHashed = PasswordSecurity.generateHash(password);
		Customer customer = CustomerDao.validateCustomer(contact, pwdHashed);
		
		
		if (customer.getCustomerID() == 0) {
			word.error = "Please provide valid credentials";
			list.add(word);
		} else {
			Account account = AccountDao.findAccountByCustomerID(customer.getCustomerID());
			word.message = "You have successfully logged in.";
			list.add(word);
			list.add(customer);
			list.add(account);

			sessionMap.put("ACCOUNT", account);
			sessionMap.put("CUSTOMER", customer);
			sessionMap.put("SIGN", new Word());
		}
		}
		data = gson.toJson(list);

		out.write(data);
		out.flush();
		out.close();

		return SUCCESS;
	}

	public String registerUser() throws IOException, ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");

		PrintWriter out = response.getWriter();

		ArrayList<Object> list = new ArrayList<>();
		Word word = new Word();
		Gson gson = new Gson();

		if (CustomerDao.checkIfPresent(contact) != 0) {
			word.error = "Errr. Looks like the mail address has been registered with some other account.";
			list.add(word);
		} else {
			String pwdHashed = PasswordSecurity.generateHash(password);

			Customer customer = new Customer(customerName, contact, dateOfBirth, gender, pwdHashed);
			int customerID = CustomerDao.getCustomerID(customer);
			customer.setCustomerID(customerID);

			long creationTime = new Date().getTime();
			float balance = accountType.minBalance;

			Account account = new Account(customerID, accountType, balance, creationTime);

			account.setAccountNumber(AccountDao.insertNewAccount(account));
			word.message = "Your account has been registered successfully.";
			list.add(word);
			list.add(customer);
			list.add(account);

			sessionMap.put("ACCOUNT", account);
			sessionMap.put("CUSTOMER", customer);
			sessionMap.put("SIGN", new Word());
		}
		data = gson.toJson(list);

		out.write(data);
		out.flush();
		out.close();

		return SUCCESS;
	}

	public String deleteUser() throws IOException, ClassNotFoundException, SQLException {
		
		ArrayList<Object> list = new ArrayList<>();
		Word word = new Word();
		Gson gson = new Gson();
		
		HttpServletResponse response = ServletActionContext.getResponse();

		Customer customer = (Customer) sessionMap.get("CUSTOMER");
		Account account = (Account) sessionMap.get("ACCOUNT");

		if (account.getBalance() == 0) {
			
			CustomerDao.deleteCustomer(customer.getCustomerID());
			sessionMap.invalidate();
			response.setContentType("text/html");
			return LOGIN;
			
		} else {
			
			word.error = "Due to the fact that your balance exceeds 0, we are not able to delete your account.";
			list.add(word);
			data = gson.toJson(list);

			response.setContentType("application/json");

			PrintWriter out = response.getWriter();

			out.write(data);
			out.flush();
			out.close();

			return SUCCESS;
		}
	}
	
	public String logOutUser() {
		sessionMap.clear();
		sessionMap.invalidate();
		return LOGIN;
	}
	
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;
	}

}

class Word {

	String error;
	String warning;
	String message;
	String time;

	public Word() {
		time = new Date().toString();
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getWarning() {
		return warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
