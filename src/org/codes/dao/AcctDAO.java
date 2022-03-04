package org.codes.dao;

import org.codes.beans.Account;
import org.codes.dao.dialects.AcctDialects;
import org.codes.utils.Records;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class AcctDAO implements AcctDialects {

    public static HashMap<Integer, Account> getAllAccounts() throws SQLException, IOException, ClassNotFoundException {

        HashMap<Integer, Account> accountsMap = new HashMap<>();

        Connection connection = Records.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ALL_ACCOUNTS);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int accountNumber = resultSet.getInt("accountNumber");
            String customerName = resultSet.getString("customerName");
            float balance = resultSet.getFloat("balance");
            String password = resultSet.getString("password");
            Timestamp createTime = resultSet.getTimestamp("createTime");
            Account account = Account.retrAccount(accountNumber, customerName, balance, password, createTime);
            accountsMap.put(accountNumber, account);
        }
        preparedStatement.close();
        connection.close();

        return accountsMap;
    }

    public static int insertAccount(Account account) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = Records.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT);
        preparedStatement.setInt(1, account.getAccountNumber());
        preparedStatement.setString(2, account.getCustomerName());
        preparedStatement.setFloat(3, account.getBalance());
        preparedStatement.setString(4, account.getPassword());
        preparedStatement.setTimestamp(5, account.getCreateTime());

        int rowsModified = preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

        return rowsModified;
    }

}
