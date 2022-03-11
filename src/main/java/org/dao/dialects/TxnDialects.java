package org.dao.dialects;

import org.dao.columns.Columns;

public interface TxnDialects extends Columns{
	
    String ALL_TRANSACTIONS_BY_ACCOUNT = "SELECT * FROM transactions WHERE accountNumber = ?";
    String INSERT_TRANSACTION = "INSERT INTO transactions(txnHash, accountNumber, amount, mode, createTime) VALUES(?, ?, ?, ?, ?)";
    String TRANSACTION_BY_HASH = "SELECT * FROM transactions WHERE txnHash = ?";
}
