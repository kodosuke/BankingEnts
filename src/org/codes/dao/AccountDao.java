package org.codes.dao;

import org.codes.beans.Account;
import org.codes.dao.dialects.AcctDialects;
import org.codes.utils.Records;
import org.codes.utils.Type;

import java.io.IOException;
import java.sql.*;

public class AccountDao implements AcctDialects, Columns {

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
            password = resultSet.getString(PASSWORD);
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
            recentAccountNumber = resultSet.getInt(ACCOUNT_NUMBER);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return recentAccountNumber;
    }

    public static int readBalance(int accountNumber) throws SQLException, IOException, ClassNotFoundException {

        int balance = 0;
        Connection connection = Records.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_BALANCE);
        preparedStatement.setInt(1, accountNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            balance = resultSet.getInt(BALANCE);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return balance;
    }


    public static Account getAccount(int accountNumber) throws SQLException, IOException, ClassNotFoundException {

        Account account = new Account();
        account.setAccountNumber(accountNumber);

        Connection connection = Records.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ACCOUNT_BY_NUMBER);
        preparedStatement.setInt(1, accountNumber);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            account.setCustomerName(resultSet.getString(CUSTOMER_NAME));
            account.setBalance(resultSet.getFloat(BALANCE));
            account.setAccountType(Type.valueOf(resultSet.getString(ACCOUNT_TYPE)));
            account.setPassword(resultSet.getString(PASSWORD));
            account.setCreateTime(resultSet.getTimestamp(ACCOUNT_TIMESTAMP));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return account;
    }

    public static void updateBalance(Account account) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = Records.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BALANCE);
        preparedStatement.setFloat(1, account.getBalance());
        preparedStatement.setInt(2, account.getAccountNumber());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

    }

    public static boolean exists(int destAccNumber) throws SQLException, IOException, ClassNotFoundException {
        int returned = 0;
        Connection connection = Records.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ACCOUNT);
        preparedStatement.setInt(1, destAccNumber);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            returned = resultSet.getInt(1);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return returned == 1;
    }

    public static void updatePassword(Account account) throws SQLException, IOException, ClassNotFoundException {
        int accountNumber = account.getAccountNumber();
        String password = account.getPassword();

        Connection connection = Records.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD);
        preparedStatement.setString(1, password);
        preparedStatement.setInt(2, accountNumber);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    public static void deleteAccount(int accountNumber) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = Records.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ACCOUNT);
        preparedStatement.setInt(1, accountNumber);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

    }
}
