package org.code.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.code.dao.AccountDao;
import org.code.dao.TransactionDao;
import org.code.enums.TransactionMode;
import org.code.models.Account;
import org.code.models.Transaction;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class BankingAction extends ActionSupport {
	
	private float amount;

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	public String creditBalance() throws ClassNotFoundException, SQLException, IOException {
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		
	    HttpServletResponse response = ServletActionContext.getResponse();
	    response.setContentType("application/json");
	    
		ArrayList<Object> list = new ArrayList<>();
		Word word = new Word();
		Gson gson = new Gson();
		
		String jsonString;
		
		PrintWriter out = response.getWriter();
		
	    Account account = (Account)session.getAttribute("ACCOUNT");
		
		if (account.deposit(amount)) {
			AccountDao.updateBalance(account);
			Transaction txn = new Transaction(account.getAccountNumber(), amount, TransactionMode.CREDIT, new Date().getTime());
			TransactionDao.insertTransaction(txn);
			word.setMessage("Your account has been credited with the amount. Current Balance " + account.getBalance());
			
			
		} else {
			word.setError("The amount should be atleast 1 rupee.");
		}
		
		list.add(word);
		list.add(account);
		session.setAttribute("ACCOUNT", account);
		
		jsonString = gson.toJson(list);
		
		out.write(jsonString);
		out.flush();
		out.close();
			
		return SUCCESS;
	}
	
	public String debitBalance() throws ClassNotFoundException, SQLException, IOException {
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		
	    HttpServletResponse response = ServletActionContext.getResponse();
	    response.setContentType("application/json");
	    
		ArrayList<Object> list = new ArrayList<>();
		Word word = new Word();
		Gson gson = new Gson();
		
		String jsonString;
		
		PrintWriter out = response.getWriter();
		
	    Account account = (Account)session.getAttribute("ACCOUNT");
		
		if (account.withdraw(amount)) {
			AccountDao.updateBalance(account);
			Transaction txn = new Transaction(account.getAccountNumber(), amount, TransactionMode.DEBIT, new Date().getTime());
			TransactionDao.insertTransaction(txn);
			word.setMessage("Your account has been debited with the amount. Current Balance " + account.getBalance());
			
			
		} else {
			word.setError("Insufficient balance.");
		}
		
		list.add(word);
		list.add(account);
		session.setAttribute("ACCOUNT", account);
		
		jsonString = gson.toJson(list);
		
		out.write(jsonString);
		out.flush();
		out.close();
			
		return SUCCESS;
	}
	
	
}
