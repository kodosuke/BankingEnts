package org.codes.dao;

import org.codes.beans.Transaction;
import org.codes.dao.dialects.TxnDialects;
import org.codes.utils.Mode;
import org.codes.utils.Records;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class TxnDAO implements TxnDialects {

    public static HashMap<Long, Transaction> getAllTransactions() throws SQLException, IOException, ClassNotFoundException {

        HashMap<Long, Transaction> transactionsMap = new HashMap<>();
        Connection connection = Records.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ALL_TRANSACTIONS);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int accountNumber = resultSet.getInt("accountNumber");
            setTxnIntoMap(transactionsMap, resultSet, accountNumber);
        }
        return transactionsMap;
    }

    private static void setTxnIntoMap(HashMap<Long, Transaction> transactionsMap, ResultSet resultSet, int accountNumber) throws SQLException {

        long txnHash = resultSet.getLong("txnHash");
        float amount = resultSet.getFloat("amount");
        Mode mode = Mode.valueOf(resultSet.getString("mode"));
        Timestamp createTime = resultSet.getTimestamp("createTime");

        Transaction txn = Transaction.retrTxn(txnHash, accountNumber, amount, mode, createTime);
        transactionsMap.put(txnHash, txn);
    }

    public static HashMap<Long, Transaction> getTransactionsByAccount(int accountNumber) throws SQLException, IOException, ClassNotFoundException {

        HashMap<Long, Transaction> transactionsMap = new HashMap<>();
        Connection connection = Records.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ALL_TRANSACTIONS_BY_ACCOUNT);
        preparedStatement.setInt(1, accountNumber);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            //            int accountNumber = resultSet.getInt("accountNumber");
            setTxnIntoMap(transactionsMap, resultSet, accountNumber);
        }
        return transactionsMap;
    }

    public static Transaction getTransactionByHash(long txnHash) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = Records.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(TRANSACTION_BY_HASH);
        preparedStatement.setLong(1, txnHash);
        ResultSet resultSet = preparedStatement.executeQuery();

        Transaction transaction = new Transaction();
        
        transaction.setTxnHash(txnHash);
        transaction.setAccountNumber(resultSet.getInt("accountNumber"));
        transaction.setAmount(resultSet.getFloat("amount"));
        transaction.setMode(Mode.valueOf(resultSet.getString("mode")));
        transaction.setCreateTime(resultSet.getTimestamp("createTime"));

        return transaction;
    }

    public static int insertTransactions(Transaction transaction) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = Records.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRANSACTION);
        preparedStatement.setLong(1, transaction.getTxnHash());
        preparedStatement.setInt(2, transaction.getAccountNumber());
        preparedStatement.setFloat(3, transaction.getAmount());
        preparedStatement.setString(4, transaction.getMode().name());
        preparedStatement.setTimestamp(5, transaction.getCreateTime());
        return preparedStatement.executeUpdate();

    }


}

