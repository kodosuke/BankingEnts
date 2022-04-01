package org.code.dao.dialects;

public interface AccountDialects {

    String ACCOUNT_NUMBER = "accountNumber";
    String CUSTOMER_ID = "customerID";
    String ACCOUNT_TYPE = "accountType";
    String BALANCE = "balance";
    String CREATION_TIME = "creationTime";

    String INSERT_ACCOUNT = "INSERT INTO accounts(" +
            "customerID, accountType, balance, creationTime)" +
            "VALUES(?, ?, ?, ?)";

    String READ_ACCOUNT = "SELECT * FROM accounts WHERE customerID = ?";
    String UPDATE_BALANCE = "UPDATE accounts SET balance = ? WHERE accountNumber = ?";
    String DELETE_ACCOUNT = "DELETE FROM accounts WHERE accountNumber = ?";
    
    String JOIN_CUSTOMER_AND_ACCOUNT = "SELECT * FROM customers JOIN accounts USING(customerID) WHERE accountNumber = ?";

}
