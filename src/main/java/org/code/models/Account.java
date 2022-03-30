package org.code.models;

import org.code.enums.AccountType;

public class Account {

    int accountNumber;
    int customerID;
    AccountType accountType;
    float balance;
    long creationTime;

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
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

    public Account() {
    }

    public Account(int customerID, AccountType accountType, float balance, long creationTime) {
        this.customerID = customerID;
        this.accountType = accountType;
        this.balance = balance;
        this.creationTime = creationTime;
    }

    public Account(int accountNumber, int customerID, AccountType accountType, float balance, long creationTime) {
        this.accountNumber = accountNumber;
        this.customerID = customerID;
        this.accountType = accountType;
        this.balance = balance;
        this.creationTime = creationTime;
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
