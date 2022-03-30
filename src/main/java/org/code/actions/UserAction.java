package org.code.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.code.dao.TransactionDao;
import org.code.models.Account;
import org.code.models.Customer;
import org.code.models.Transaction;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements SessionAware {

	private SessionMap<String, Object> sessionMap;
	String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;
	}

	public String readUser() throws IOException {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");

		PrintWriter out = response.getWriter();

		ArrayList<Object> list = new ArrayList<>();
		Gson gson = new Gson();

		Customer customer = (Customer) sessionMap.get("CUSTOMER");
		Account account = (Account) sessionMap.get("ACCOUNT");

		list.add(customer);
		list.add(account);

		data = gson.toJson(list);

		out.write(data);
		out.flush();
		out.close();
		
		return SUCCESS;
	}
	
	public String getTransactions() throws ClassNotFoundException, SQLException, IOException {
		
	    HttpServletResponse response = ServletActionContext.getResponse();
	    response.setContentType("application/json");
	    
	    PrintWriter out = response.getWriter();
	    
		Gson gson = new Gson();
		
		Account account = (Account) sessionMap.get("ACCOUNT");
		
		ArrayList<Transaction> list = TransactionDao.getTransactionsByAccount(account.getAccountNumber());
		data = gson.toJson(list);
		
		out.write(data);
		out.flush();
		out.close();
		
		return SUCCESS;
	}
}
