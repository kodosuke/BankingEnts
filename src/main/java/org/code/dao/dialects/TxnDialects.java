package org.code.dao.dialects;

public interface TxnDialects{
	
    String TXN_NUMBER = "txnHash";
    String MODE = "mode";
    String TXN_TIMESTAMP = "creationTime";
    String AMOUNT = "amount";
  	String ACCOUNT_NUMBER = "accountNumber";
  	String CLOSING_BALANCE = "closingBalance";
  	String DESCRIPTION = "description";
  	
    String ALL_TRANSACTIONS_BY_ACCOUNT = "SELECT * FROM transactions WHERE accountNumber = ?";
    String INSERT_TRANSACTION = "INSERT INTO transactions(txnHash, accountNumber, amount, mode, creationTime, closingBalance, description) VALUES(?, ?, ?, ?, ?, ?, ?)";
    String TRANSACTION_BY_HASH = "SELECT * FROM transactions WHERE txnHash = ?";
}
