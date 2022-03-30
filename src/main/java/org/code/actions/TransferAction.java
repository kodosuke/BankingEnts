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
import org.code.dao.CustomerDao;
import org.code.dao.TransactionDao;
import org.code.enums.TransactionMode;
import org.code.models.Account;
import org.code.models.Transaction;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class TransferAction extends ActionSupport{

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

		String data;

		PrintWriter out = response.getWriter();

		Account account = (Account) session.getAttribute("ACCOUNT");

		int recipientID = CustomerDao.checkIfPresent(recipientContact);
		Transaction txn;

		if (recipientID != 0) {
			Account destination = AccountDao.findAccountByCustomerID(recipientID);
			if (destination.getAccountNumber() == recipientAccountNumber) {
				if (destination.getAccountNumber() == account.getAccountNumber()) {
					word.setError("Cannot transfer to your own account.");
				} else {
					if (account.withdraw(amount)) {
						AccountDao.updateBalance(account);
						txn = new Transaction(account.getAccountNumber(), amount, TransactionMode.DEBIT,
								new Date().getTime());
						TransactionDao.insertTransaction(txn);
						destination.deposit(amount);
						AccountDao.updateBalance(destination);
						txn = new Transaction(destination.getAccountNumber(), amount, TransactionMode.CREDIT,
								new Date().getTime());
						TransactionDao.insertTransaction(txn);
						word.setMessage("Amount successfully transferred to " + recipientAccountNumber);
					} else {
						word.setError("Insufficient Balance.");
					}
				}
			} else {
				word.setError("No account found.");
			}
		} else {
			word.setError("No account found.");
		}
		list.add(word);
		list.add(account);
		session.setAttribute("ACCOUNT", account);

		data = gson.toJson(list);

		out.write(data);
		out.flush();
		out.close();

		return SUCCESS;
	}
}
