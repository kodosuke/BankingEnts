package org.codes.dao.dialects;

public interface TxnDialects {
    String ALL_TRANSACTIONS = "SELECT * FROM transactions";
    String ALL_TRANSACTIONS_BY_ACCOUNT = "SELECT * FROM transactions WHERE accountNumber = ?";
    String INSERT_TRANSACTION = "INSERT INTO transactions(txnHash, accountNumber, amount, mode, createTime) VALUES(?, ?, ?, ?, ?)";
    String TRANSACTION_BY_HASH = "SELECT * FROM transactions WHERE txnHash = ?";
}
