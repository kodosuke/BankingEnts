package org.dao.dialects;

import org.dao.columns.Columns;

public interface AccountDialects extends Columns{
		
		String INSERT_ACCOUNT = "INSERT INTO accounts("
				+ "customerName, contact, dateOfBirth, gender, password, type, balance, creationTime"
				+ ") VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		String READ_ACCOUNT_BY_NUMBER   = "SELECT * FROM accounts WHERE accountNumber = ?";
		String DELETE_ACCOUNT = "DELETE FROM accounts WHERE accountNumber = ?";
		
		String READ_PASSWORD_BY_NUMBER   = "SELECT password FROM accounts WHERE accountNumber = ?";
		
		String UPDATE_PASSWORD = "UPDATE accounts SET password = ? WHERE accountNumber = ?";
		
		String UPDATE_BALANCE = "UPDATE accounts SET balance = ? WHERE accountNumber = ?";
		
		String CHECK_CONTACT = "SELECT 1 FROM accounts WHERE contact = ? WHERE LIMIT 1";
		String LOAD_ACCOUNT = "SELECT * FROM accounts WHERE contact = ?";
		
		String IF_ACCOUNT 	  = "SELECT 1 FROM accounts WHERE CONTACT = ? LIMIT 1";	

		String READ_PASSWORD_BY_CONTACT   = "SELECT password FROM accounts WHERE contact = ?";
		String READ_ACCOUNT = "SELECT * FROM accounts WHERE contact = ? AND password = ?";
		
		String FIND_ACCOUNT = "SELECT * FROM accounts WHERE accountNumber = ? AND contact = ?";
	}

