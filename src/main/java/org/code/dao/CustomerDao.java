 	package org.code.dao;

import org.code.dao.dialects.CustomerDialects;
import org.code.enums.Gender;
import org.code.models.Customer;
import org.code.utils.DBConnection;

import java.io.IOException;
import java.sql.*;

public class CustomerDao implements CustomerDialects {

    public static int insertNewCustomer(Customer customer) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, customer.getCustomerName());
        preparedStatement.setString(2, customer.getContact());
        preparedStatement.setString(3, customer.getDateOfBirth());
        preparedStatement.setString(4, customer.getGender().name());
        preparedStatement.setString(5, customer.getPassword());
        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        int customerID = 1;
        while (resultSet.next()) {
            customerID = resultSet.getInt(1);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return customerID;
    }

    public static int checkIfPresent(String contact) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CHECK_IF_EXISTS_CUSTOMER);
        preparedStatement.setString(1, contact);
        ResultSet resultSet = preparedStatement.executeQuery();

        int returned = 0;
        while (resultSet.next()) {
            returned = resultSet.getInt(1);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return returned;
    };

    public static int getCustomerID(Customer customer) throws SQLException, ClassNotFoundException {

        int returned = CustomerDao.checkIfPresent(customer.getContact());
        if(returned != 0) {
            return returned;
        } else
            return CustomerDao.insertNewCustomer(customer);
    }

    public static Customer getCustomer(int customerID) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_CUSTOMER);
        preparedStatement.setInt(1, customerID);
        ResultSet resultSet = preparedStatement.executeQuery();

        Customer customer = new Customer();
        while (resultSet.next()) {
            customer.setCustomerID(customerID);
            customer.setCustomerName(resultSet.getString(CUSTOMER_NAME));
            customer.setContact(resultSet.getString(CONTACT));
            customer.setDateOfBirth(resultSet.getString(BIRTHDATE));
            customer.setGender(Gender.valueOf(resultSet.getString(GENDER)));
            customer.setPassword(resultSet.getString(PASSWORD));
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return customer;
    }
    
    public static Customer validateCustomer(String contact, String password) throws ClassNotFoundException, SQLException {
		
    	Customer customer = new Customer();
    	customer.setContact(contact);
    	customer.setPassword(password);
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE_CUSTOMER);
        preparedStatement.setString(1, contact);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while(resultSet.next()) {
            customer.setCustomerID(resultSet.getInt(CUSTOMER_ID));
            customer.setCustomerName(resultSet.getString(CUSTOMER_NAME));
            customer.setDateOfBirth(resultSet.getString(BIRTHDATE));
            customer.setGender(Gender.valueOf(resultSet.getString(GENDER)));
        }
    	
        resultSet.close();
        preparedStatement.close();
        connection.close();

    	
    	return customer;
    	
    }

	public static void updatePassword(Customer customer) throws SQLException, IOException, ClassNotFoundException {

			int customerID = customer.getCustomerID();
	        String password = customer.getPassword();

	        Connection connection = DBConnection.getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD);
	        preparedStatement.setString(1, password);
	        preparedStatement.setInt(2, customerID);
	        preparedStatement.executeUpdate();
	        
	        preparedStatement.close();
	        connection.close();		
	}
	
    public static void deleteCustomer(int customerID) throws SQLException, IOException, ClassNotFoundException {
    	
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER);
        preparedStatement.setInt(1, customerID);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

    }
}
