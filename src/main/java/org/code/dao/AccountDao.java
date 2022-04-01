package org.code.dao;

import org.code.dao.dialects.AccountDialects;
import org.code.enums.AccountType;
import org.code.models.Account;
import org.code.utils.DBConnection;

import java.io.IOException;
import java.sql.*;

public class AccountDao implements AccountDialects {

    public static int insertNewAccount(Account account) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, account.getCustomerID());
        preparedStatement.setString(2, account.getAccountType().toString());
        preparedStatement.setFloat(3, account.getBalance());
        preparedStatement.setLong(4, account.getCreationTime());
        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        int accountNumber = 1;
        while (resultSet.next()) {
            accountNumber = resultSet.getInt(1);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        return accountNumber;
    }
    
    public static Account findAccountByCustomerID(int customerID) throws SQLException, ClassNotFoundException {
    	
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(READ_ACCOUNT);
        preparedStatement.setInt(1, customerID);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        Account account = new Account();
        while(resultSet.next()) {
        	account.setAccountNumber(resultSet.getInt(ACCOUNT_NUMBER));
        	account.setAccountType(AccountType.valueOf(resultSet.getString(ACCOUNT_TYPE)));
        	account.setBalance(resultSet.getFloat(BALANCE));
        	account.setCreationTime(resultSet.getLong(CREATION_TIME));
        	account.setCustomerID(customerID);
        }
        
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        return account;
    }

	public static void updateBalance(Account account) throws ClassNotFoundException, SQLException {
		
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BALANCE);
        preparedStatement.setFloat(1, account.getBalance());
        preparedStatement.setInt(2, account.getAccountNumber());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
		
	}
	
    public static void deleteAccount(int accountNumber) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ACCOUNT);
        preparedStatement.setInt(1, accountNumber);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

    }
    
    public static ResultSet getCompleteInfoOnAccount( int accountNumber) throws ClassNotFoundException, SQLException {
		
    	Connection connection = DBConnection.getConnection();
    	PreparedStatement preparedStatement = connection.prepareStatement(JOIN_CUSTOMER_AND_ACCOUNT);
    	preparedStatement.setInt(1, accountNumber);
    	return preparedStatement.executeQuery();
    }
    
}
