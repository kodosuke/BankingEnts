package org.code.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.code.dao.AccountDao;
import org.code.dao.CustomerDao;
import org.code.dao.TransactionDao;
import org.code.enums.AccountType;
import org.code.enums.TransactionMode;
import org.code.models.Account;
import org.code.models.Customer;
import org.code.models.Transaction;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class TransferAction extends ActionSupport {

	private int recipientAccountNumber;
	private String recipientContact;
	private float amount;

	public int getRecipientAccountNumber() {
		return recipientAccountNumber;
	}

	public void setRecipientAccountNumber(int recipientAccountNumber) {
		this.recipientAccountNumber = recipientAccountNumber;
	}

	public String getRecipientContact() {
		return recipientContact;
	}

	public void setRecipientContact(String recipientContact) {
		this.recipientContact = recipientContact;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	
	public String transferBalance() throws IOException, ClassNotFoundException, SQLException {
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");	
		
		ArrayList<Object> list = new ArrayList<>();
		Word word = new Word();
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		String data;
		
		
		
		Account src = (Account) session.getAttribute("ACCOUNT");
		Customer sender = (Customer) session.getAttribute("CUSTOMER");
		
		ResultSet resultSet = AccountDao.getCompleteInfoOnAccount(recipientAccountNumber);
		
		Account dest = new Account();
		Customer recipient = new Customer();
		
		while(resultSet.next()) {
			
			dest.setAccountNumber(resultSet.getInt("accountNumber"));
			dest.setBalance(resultSet.getFloat("balance"));
			dest.setAccountType(AccountType.valueOf(resultSet.getString("accountType")));
			dest.setCustomerID(resultSet.getInt("customerID"));
			recipient.setContact(resultSet.getString("contact"));
			recipient.setCustomerID(resultSet.getInt("customerID"));
			recipient.setCustomerName(resultSet.getString("customerName"));
			
		}
		
		if(!recipient.equals(recipientContact) && recipientAccountNumber != src.getAccountNumber()) {
			
			if(src.withdraw(amount)) {
				AccountDao.updateBalance(src);
				
				String srcDescr = "Received by " + recipient.getCustomerName() + " #" + recipient.getCustomerID() + ", " + recipient.getContact() + " on the account "+ dest.getAccountNumber();
				Transaction srcTxn = new Transaction(src.getAccountNumber(), amount, TransactionMode.DEBIT_BY_TRANSFER , new Date().getTime(), src.getBalance(), srcDescr);
				TransactionDao.insertTransaction(srcTxn);
				
				dest.deposit(amount);
				AccountDao.updateBalance(dest);
				
				String destDescr = "Sent by " + sender.getCustomerName() + " #"+ sender.getCustomerID() + " , " + sender.getContact() + " from the account " + src.getAccountNumber();
				Transaction destTxn = new Transaction(dest.getAccountNumber(), amount, TransactionMode.CREDIT_BY_TRANSFER, new Date().getTime(), dest.getBalance(), destDescr);
				TransactionDao.insertTransaction(destTxn);
				
				word.setMessage("Amount "+ amount + " successfully transferred to " + recipientAccountNumber);
			} else {
				word.setError("Insufficient Balance.");
			}
			
		} else {
			word.setError("Invalid account details.");
		}
		
		System.out.println(word.toString());
		
		list.add(word);
		list.add(src);
		session.setAttribute("ACCOUNT", src);
		
		data = gson.toJson(list);
		
		
		out.write(data);
		out.flush();
		out.close();		
		
		return SUCCESS;
	}
}
