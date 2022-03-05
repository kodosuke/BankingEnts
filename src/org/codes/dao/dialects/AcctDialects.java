package org.codes.dao.dialects;

public interface AcctDialects {
    String ALL_ACCOUNTS = "SELECT * FROM accounts";
    String ACCOUNT_BY_NUMBER = "SELECT * FROM accounts WHERE accountNumber = ?";
    String UPDATE_BALANCE = "UPDATE accounts SET balance = ? WHERE accountNumber = ?";
    String UPDATE_PASSWORD = "UPDATE accounts SET password = ? WHERE accountNumber = ?";
    String RETRIEVE_PASSWORD = "SELECT password FROM accounts WHERE accountNumber = ?";
    String INSERT_ACCOUNT = "INSERT INTO accounts(accountNumber, customerName, balance, password, createTime) VALUES(?, ?, ?, ?, ?)";
    String GET_BALANCE = "SELECT balance FROM accounts WHERE accountNumber = ?";
}
