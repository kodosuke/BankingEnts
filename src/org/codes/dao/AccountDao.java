package org.codes.dao;

import org.codes.beans.Account;
import org.codes.dao.dialects.AcctDialects;
import org.codes.utils.Records;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountDao implements AcctDialects {

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
