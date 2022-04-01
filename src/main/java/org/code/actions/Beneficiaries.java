package org.code.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.code.dao.AccountDao;
import org.code.dao.BeneficiaryDao;
import org.code.dao.CustomerDao;
import org.code.models.Account;
import org.code.models.Beneficiary;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class Beneficiaries extends ActionSupport{

	private int beneficiaryAccountNumber;
	private String beneficiaryContact;
	
	public int getBeneficiaryAccountNumber() {
		return beneficiaryAccountNumber;
	}
	public void setBeneficiaryAccountNumber(int beneficiaryAccountNumber) {
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
	}
	public String getBeneficiaryContact() {
		return beneficiaryContact;
	}
	public void setBeneficiaryContact(String beneficiaryContact) {
		this.beneficiaryContact = beneficiaryContact;
	}
	
	public String addBeneficiary() throws ClassNotFoundException, SQLException, IOException {

		HttpSession session = ServletActionContext.getRequest().getSession();

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");

		ArrayList<Object> list = new ArrayList<>();
		Word word = new Word();
		Gson gson = new Gson();

		String jsonString;

		PrintWriter out = response.getWriter();

		int recipientID = CustomerDao.checkIfPresent(beneficiaryContact);
		int sender = ((Account) session.getAttribute("ACCOUNT")).getAccountNumber();
		
		if (recipientID != 0 ) {
			
			Account destination = AccountDao.findAccountByCustomerID(recipientID);
			if (destination.getAccountNumber() == beneficiaryAccountNumber) {
				
				if (! BeneficiaryDao.checkIfPresent(sender, beneficiaryAccountNumber)) {

				BeneficiaryDao.insertBeneficiary(sender, beneficiaryAccountNumber);
				word.setMessage("Beneficiary added.");
				
				} else {
					word.setError("Beneficiary already exists.");
				}
			} else {
				word.setError("No account found.");
			}
		} else {
			word.setError("No account found.");
		}
			list.add(word);
	
			jsonString = gson.toJson(list);
	
			out.write(jsonString);
			out.flush();
			out.close();

		return SUCCESS;
	}
	
	public String getBeneficiaries() throws IOException, ClassNotFoundException, SQLException {

		HttpSession session = ServletActionContext.getRequest().getSession();

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");

		Gson gson = new Gson();

		String data;

		PrintWriter out = response.getWriter();
		
		int sender = ((Account) session.getAttribute("ACCOUNT")).getAccountNumber();
		ArrayList<Beneficiary> list = BeneficiaryDao.getBeneficiaries(sender);
		
		data = gson.toJson(list);
		
		out.write(data);
		out.flush();
		out.close();
		
		return SUCCESS;		
	}
	
	
}
