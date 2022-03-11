package org.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.beans.Account;
import org.dao.dialects.AccountDialects;
import org.utils.DBConnection;
import org.utils.enums.Type;

public class AccountDao implements AccountDialects{
    public static int insertAccount(Account account) throws SQLException, IOException, ClassNotFoundException {

    	int accountNumber = 1001;
    	
        Connection connection = DBConnection.connect();
        
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
        
        preparedStatement.setString(1, account.getCustomerName());
        preparedStatement.setFloat(2, account.getBalance());
        preparedStatement.setString(3, account.getPassword());
        preparedStatement.setLong(4, account.getCreateTime());
        preparedStatement.setString(5,account.getAccountType().name());        
        preparedStatement.executeUpdate();
        
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        
        while(resultSet.next()) {
        	accountNumber = resultSet.getInt(1);
        }

        preparedStatement.close();
        resultSet.close();
        connection.close();

        return accountNumber;
    }
    
    public static boolean checkIfExists(int accountNumber) throws SQLException, ClassNotFoundException {
    	
    	int returned = 0;
    	
        Connection connection = DBConnection.connect();
        PreparedStatement preparedStatement = connection.prepareStatement(IF_ACCOUNT);
        preparedStatement.setInt(1, accountNumber);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            returned = resultSet.getInt(1);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
        
		return returned == 1;
    	
    }
    
    public static Account getAccountByNumber(int accountNumber) throws ClassNotFoundException, SQLException {
    	
        Account account = new Account();
        account.setAccountNumber(accountNumber);

        Connection connection = DBConnection.connect();
        PreparedStatement preparedStatement = connection.prepareStatement(READ_ACCOUNT);
        preparedStatement.setInt(1, accountNumber);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            account.setCustomerName(resultSet.getString(CUSTOMER_NAME));
            account.setBalance(resultSet.getFloat(BALANCE));
            account.setAccountType(Type.valueOf(resultSet.getString(ACCOUNT_TYPE)));
            account.setPassword(resultSet.getString(PASSWORD));
            account.setCreateTime(resultSet.getLong(ACCOUNT_TIMESTAMP));
        }
        
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return account;
    }
    public static void updateBalance(Account account) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = DBConnection.connect();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BALANCE);
        preparedStatement.setFloat(1, account.getBalance());
        preparedStatement.setInt(2, account.getAccountNumber());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

    }
    

    public static void updatePassword(Account account) throws SQLException, IOException, ClassNotFoundException {
        int accountNumber = account.getAccountNumber();
        String password = account.getPassword();

        Connection connection = DBConnection.connect();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD);
        preparedStatement.setString(1, password);
        preparedStatement.setInt(2, accountNumber);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    public static void deleteAccount(int accountNumber) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = DBConnection.connect();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ACCOUNT);
        preparedStatement.setInt(1, accountNumber);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

    }
}
    
    
    
    
