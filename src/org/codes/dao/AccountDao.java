package org.codes.dao;

import org.codes.beans.Account;
import org.codes.dao.dialects.AcctDialects;
import org.codes.utils.Records;
import org.codes.utils.Type;

import java.io.IOException;
import java.sql.*;

public class AccountDao implements AcctDialects {

    public static int insertAccount(Account account) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = Records.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT);
        preparedStatement.setInt(1, account.getAccountNumber());
        preparedStatement.setString(2, account.getCustomerName());
        preparedStatement.setFloat(3, account.getBalance());
        preparedStatement.setString(4, account.getPassword());
        preparedStatement.setTimestamp(5, account.getCreateTime());
        preparedStatement.setString(6,account.getAccountType().name());

        int rowsModified = preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

        return rowsModified;
    }

    public static String readPassword(int accountNumber) throws SQLException, IOException, ClassNotFoundException {

        String password = null;
        Connection connection = Records.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(RETRIEVE_PASSWORD);
        preparedStatement.setInt(1, accountNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            password = resultSet.getString("password");
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return password;
    }

    public static int registerAccount(int accountNumber, String customerName, Type accountType, float balance, String password, Timestamp createTime) throws SQLException, IOException, ClassNotFoundException {
        Account account = Account.retrAccount(accountNumber, customerName, accountType, balance, password, createTime);
        return AccountDao.insertAccount(account);
    }

    public static int readLastAccountNumber() throws SQLException, IOException, ClassNotFoundException {

        int recentAccountNumber = 1000;
        Connection connection = Records.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(READ_RECENT_ACCOUNT);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            recentAccountNumber = resultSet.getInt("accountNumber");
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return recentAccountNumber;
    }
}
