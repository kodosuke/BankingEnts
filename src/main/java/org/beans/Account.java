package org.beans;

import org.enums.Type;
import org.enums.Gender;

public class Account {

		int accountNumber;
		String customerName;
		String contact;
		String dateOfBirth;
		Gender gender;
		String password;
		Type accountType;
		float balance;
		long creationTime;
			
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
		
		public Account(int accountNumber, String customerName, String contact, String dateOfBirth, Gender gender,
				String password, Type accountType, float balance, long creationTime) {
			super();
			this.accountNumber = accountNumber;
			this.customerName = customerName;
			this.contact = contact;
			this.dateOfBirth = dateOfBirth;
			this.gender = gender;
			this.password = password;
			this.accountType = accountType;
			this.balance = balance;
			this.creationTime = creationTime;
		}
		
		public Account(String customerName, String contact, String dateOfBirth, Gender gender, String password,
				Type accountType, float balance, long creationTime) {
			super();
			this.customerName = customerName;
			this.contact = contact;
			this.dateOfBirth = dateOfBirth;
			this.gender = gender;
			this.password = password;
			this.accountType = accountType;
			this.balance = balance;
			this.creationTime = creationTime;
		}
		
		public Account() {
			super();
		}
		
		public boolean deposit(float amount) {
			if(amount > 0) {
				this.setBalance(this.getBalance() + amount);
				return true;
			}
			return false;
		}	
		
		public boolean withdraw(float amount) {
			if(this.getBalance() >= amount && amount > 0) {
				this.setBalance(this.getBalance() - amount);
				return true;
			}
			return false;
		}
	
		
}
