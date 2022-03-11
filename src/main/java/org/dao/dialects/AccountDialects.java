package org.dao.dialects;

import org.dao.columns.Columns;

public interface AccountDialects extends Columns{
	
	String INSERT_ACCOUNT = "INSERT INTO accounts("
			+ "customerName, balance, password, createTime, type"
			+ ") VALUES(?, ?, ?, ?, ?)";
	
	String READ_ACCOUNT   = "SELECT * FROM accounts WHERE accountNumber = ?";
	String IF_ACCOUNT 	  = "SELECT 1 FROM accounts WHERE accountNumber = ? LIMIT 1";
	String DELETE_ACCOUNT = "DELETE FROM accounts WHERE accountNumber = ?";
	
	String READ_PASSWORD   = "SELECT password FROM accounts WHERE accountNumber = ?";
	String UPDATE_PASSWORD = "UPDATE accounts SET password = ? WHERE accountNumber = ?";
	
	String UPDATE_BALANCE = "UPDATE accounts SET balance = ? WHERE accountNumber = ?";
	

	
}
