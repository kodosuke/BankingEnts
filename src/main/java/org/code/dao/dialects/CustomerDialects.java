package org.code.dao.dialects;

public interface CustomerDialects {

    String CUSTOMER_ID = "customerID";
    String CONTACT = "contact";
    String CUSTOMER_NAME = "customerName";
    String PASSWORD = "password";
    String GENDER = "gender";
    String BIRTHDATE = "dateOfBirth";

    String INSERT_CUSTOMER =
            "INSERT INTO customers( customerName, contact, dateOfBirth, gender, password)" +
                    "VALUES(?, ?, ?, ?, ?)";
    String CHECK_IF_EXISTS_CUSTOMER =
            "SELECT customerID FROM customers WHERE contact = ? LIMIT 1";
    String FIND_CUSTOMER =
            "SELECT * FROM customers WHERE customerID = ?";
    String VALIDATE_CUSTOMER = 
    		"SELECT * FROM customers WHERE contact = ? AND password = ?";
    
    String UPDATE_PASSWORD = "UPDATE customers SET password = ? WHERE customerID = ?";
    String DELETE_CUSTOMER = "DELETE FROM customers WHERE customerID = ?";
}
