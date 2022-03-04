package org.codes.beans;

import java.sql.Timestamp;

public class Account {

    int accountNumber;
    String customerName;
    float balance;
    String password;
    Timestamp createTime;

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

    public static Account retrAccount(int accountNumber, String customerName, float balance, String password, Timestamp createTime) {

        Account account = new Account();

        account.setAccountNumber(accountNumber);
        account.setCustomerName(customerName);
        account.setBalance(balance);
        account.setPassword(password);
        account.setCreateTime(createTime);

        return account;
    }
}
