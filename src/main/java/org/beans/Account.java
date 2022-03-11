package org.beans;

import org.utils.enums.Type;

public class Account {

    int accountNumber;
    String customerName;
    float balance;
    String password;
    long createTime;
    Type accountType;
    
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
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public Type getAccountType() {
		return accountType;
	}
	public void setAccountType(Type accountType) {
		this.accountType = accountType;
	}

	public Account() {
		super();
	}
	public Account(String customerName, float balance, String password, long createTime, Type accountType) {
		super();
		this.customerName = customerName;
		this.balance = balance;
		this.password = password;
		this.createTime = createTime;
		this.accountType = accountType;
	}
	
	public Account(int accountNumber, String customerName, float balance, String password, long createTime,
			Type accountType) {
		super();
		this.accountNumber = accountNumber;
		this.customerName = customerName;
		this.balance = balance;
		this.password = password;
		this.createTime = createTime;
		this.accountType = accountType;
	}
    
	public boolean deposit(float amount) {

        if (amount >= 1) {
            this.balance += amount;
            System.out.println("Dear customer, Account " + this.accountNumber + " has been credited with $ " + amount +
                    " . Your current account balance is $ " + this.balance + " .");
            return true;

        } else {
            System.out.println("Minimum deposit is $ 1 .");
            return false;
        }
    }

    public boolean withdraw(float amount) {

        if (amount <= this.balance && amount > 0) {
            this.balance -= amount;
            System.out.println("Dear customer, Account " + this.accountNumber + " has been debited with $ " + amount + " . Your current account balance is $ " + this.balance + " .");
            return true;
        } else {
            System.out.println("Dear customer, your account balance is insufficient for making this transaction.");
            return false;
        }
    }
}
