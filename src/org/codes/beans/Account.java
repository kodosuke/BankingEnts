package org.codes.beans;

import org.codes.utils.Type;

import java.sql.Timestamp;

public class Account {

    int accountNumber;
    String customerName;
    float balance;
    String password;
    Timestamp createTime;
    Type accountType;

    public Type getAccountType() {
        return accountType;
    }

    public void setAccountType(Type accountType) {
        this.accountType = accountType;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public static Account retrAccount(int accountNumber, String customerName, Type accountType, float balance, String password, Timestamp createTime) {

        Account account = new Account();

        account.setAccountNumber(accountNumber);
        account.setCustomerName(customerName);
        account.setBalance(balance);
        account.setPassword(password);
        account.setCreateTime(createTime);
        account.setAccountType(accountType);

        return account;
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

        if (amount <= this.balance) {
            this.balance -= amount;
            System.out.println("Dear customer, Account " + this.accountNumber + " has been debited with $ " + amount + " . Your current account balance is $ " + this.balance + " .");
            return true;
        } else {
            System.out.println("Dear customer, your account balance is insufficient for making this transaction.");
            return false;
        }
    }

//    public Account transfer(Account destination, float amount) {
//
//        if(this.withdraw(amount)) {
//
//        }
//    }


}

