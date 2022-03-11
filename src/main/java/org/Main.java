package org;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.beans.Account;
import org.dao.dialects.AccountDialects;
import org.dao.dialects.TxnDialects;
import org.utils.DBConnection;
import org.utils.enums.Type;

public class Main implements AccountDialects{

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		int accountNumber;
		
		Account account = new Account("aliens", 0, "aliens", new Date().getTime(), Type.CURRENT);
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

	}
}
