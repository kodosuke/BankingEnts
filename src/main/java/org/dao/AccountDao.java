package org.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.beans.Account;
import org.dao.dialects.AccountDialects;
import org.enums.Gender;
import org.enums.Type;
import org.utils.DataBaseCon;

public class AccountDao implements AccountDialects{

    public static int insertAccount(Account account) throws SQLException, IOException, ClassNotFoundException {

    	int accountNumber = 786001;
    	
        Connection connection = DataBaseCon.getConnection();
        
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
        
        preparedStatement.setString(1, account.getCustomerName());
        preparedStatement.setString(2, account.getContact());
        preparedStatement.setString(3, account.getDateOfBirth());
        preparedStatement.setString(4, account.getGender().toString());
        preparedStatement.setString(5, account.getPassword());
        preparedStatement.setString(6, account.getAccountType().toString());
        preparedStatement.setFloat(7, account.getBalance());
        preparedStatement.setLong(8, account.getCreationTime());
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

	public static boolean checkIfAvailable(String contact) throws SQLException, ClassNotFoundException {
		
		int returned = 0;
    	
        Connection connection = DataBaseCon.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(IF_ACCOUNT);
        preparedStatement.setString(1, contact);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            returned = resultSet.getInt(1);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
        
		return returned == 1;
	}
	
	public static Account getAccountByNumber(String contact, String password) throws ClassNotFoundException, SQLException {
    	
        Account account = new Account();
        account.setContact(contact);
        account.setPassword(password);

        Connection connection = DataBaseCon.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(READ_ACCOUNT);
        preparedStatement.setString(1, contact);
        preparedStatement.setString(2, password);
        
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
        	
        	account.setAccountNumber(resultSet.getInt(ACCOUNT_NUMBER));
        	account.setCustomerName(resultSet.getString(CUSTOMER_NAME));
        	account.setBalance(resultSet.getFloat(BALANCE));
        	account.setGender(Gender.valueOf(resultSet.getString(GENDER)));
        	account.setDateOfBirth(resultSet.getString(BIRTHDATE));
        	account.setAccountType(Type.valueOf(resultSet.getString(ACCOUNT_TYPE)));
        	account.setCreationTime(resultSet.getLong(ACCOUNT_TIMESTAMP));
        

        }
        
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return account;
    }

	public static void updateBalance(Account account) throws ClassNotFoundException, SQLException {
	  
	        Connection connection = DataBaseCon.getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BALANCE);
	        preparedStatement.setFloat(1, account.getBalance());
	        preparedStatement.setInt(2, account.getAccountNumber());
	        preparedStatement.executeUpdate();
	        preparedStatement.close();
	        connection.close();

	    }
	
	public static Account getAccount(int accountNumber,String contact) throws SQLException, ClassNotFoundException {
		
        Account account = new Account();
        account.setContact(contact);
        account.setAccountNumber(accountNumber);
        
        Connection connection = DataBaseCon.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ACCOUNT);
        preparedStatement.setInt(1, accountNumber);
        preparedStatement.setString(2, contact);
        
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
        	
        	account.setPassword(resultSet.getString(PASSWORD));
        	account.setCustomerName(resultSet.getString(CUSTOMER_NAME));
        	account.setBalance(resultSet.getFloat(BALANCE));
        	account.setGender(Gender.valueOf(resultSet.getString(GENDER)));
        	account.setDateOfBirth(resultSet.getString(BIRTHDATE));
        	account.setAccountType(Type.valueOf(resultSet.getString(ACCOUNT_TYPE)));
        	account.setCreationTime(resultSet.getLong(ACCOUNT_TIMESTAMP));

        }
        
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return account;
		
	}
	
    public static void updatePassword(Account account) throws SQLException, IOException, ClassNotFoundException {
        int accountNumber = account.getAccountNumber();
        String password = account.getPassword();

        Connection connection = DataBaseCon.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD);
        preparedStatement.setString(1, password);
        preparedStatement.setInt(2, accountNumber);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
    public static void deleteAccount(int accountNumber) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = DataBaseCon.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ACCOUNT);
        preparedStatement.setInt(1, accountNumber);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

    }

	}
