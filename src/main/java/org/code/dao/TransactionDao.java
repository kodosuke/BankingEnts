package org.code.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.code.dao.dialects.TxnDialects;
import org.code.enums.TransactionMode;
import org.code.models.Transaction;
import org.code.utils.DBConnection;

public class TransactionDao implements TxnDialects{
    public static int insertTransaction(Transaction transaction) throws SQLException, IOException, ClassNotFoundException, SQLException, IOException {

        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRANSACTION);
        preparedStatement.setLong(1, transaction.getTxnHash());
        preparedStatement.setInt(2, transaction.getAccountNumber());
        preparedStatement.setFloat(3, transaction.getAmount());
        preparedStatement.setString(4, transaction.getMode().name());
        preparedStatement.setLong(5, transaction.getCreationTime());
        preparedStatement.setFloat(6, transaction.getClosingBalance());
        preparedStatement.setString(7, transaction.getDescription());
        int rowsModified = preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

        return rowsModified;
    }
    
    public static ArrayList<Transaction> getTransactionsByAccount(int accountNumber) throws SQLException, IOException, ClassNotFoundException {

        ArrayList<Transaction> transactions = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ALL_TRANSACTIONS_BY_ACCOUNT);
        preparedStatement.setInt(1, accountNumber);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            
            long txnHash = resultSet.getLong(TXN_NUMBER);
            float amount = resultSet.getFloat(AMOUNT);
            TransactionMode mode = TransactionMode.valueOf(resultSet.getString(MODE));
            long createTime = resultSet.getLong(TXN_TIMESTAMP);
            float closingBalance = resultSet.getFloat(CLOSING_BALANCE);
            String description = resultSet.getString(DESCRIPTION);

            Transaction txn = new Transaction(txnHash, accountNumber, amount, mode, createTime, closingBalance ,description);
            transactions.add(txn);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return transactions;
    }
}
