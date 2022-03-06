package org.codes.dao;

import org.codes.beans.Transaction;
import org.codes.dao.columns.Columns;
import org.codes.dao.dialects.TxnDialects;
import org.codes.utils.Mode;
import org.codes.utils.Records;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class TxnDao implements TxnDialects, Columns {

    public static int insertTransaction(Transaction transaction) throws SQLException, IOException, ClassNotFoundException, SQLException, IOException {

        Connection connection = Records.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRANSACTION);
        preparedStatement.setLong(1, transaction.getTxnHash());
        preparedStatement.setInt(2, transaction.getAccountNumber());
        preparedStatement.setFloat(3, transaction.getAmount());
        preparedStatement.setString(4, transaction.getMode().name());
        preparedStatement.setTimestamp(5, transaction.getCreateTime());
        int rowsModified = preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

        return rowsModified;
    }

    public static Transaction readTransaction(long txnHash) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = Records.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(TRANSACTION_BY_HASH);
        preparedStatement.setLong(1, txnHash);
        ResultSet resultSet = preparedStatement.executeQuery();

        Transaction transaction = new Transaction();

        transaction.setTxnHash(txnHash);
        transaction.setAccountNumber(resultSet.getInt(ACCOUNT_NUMBER));
        transaction.setAmount(resultSet.getFloat(AMOUNT));
        transaction.setMode(Mode.valueOf(resultSet.getString(MODE)));
        transaction.setCreateTime(resultSet.getTimestamp(TXN_TIMESTAMP));

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return transaction;

    }

    public static HashMap<Long, Transaction> getTransactionsByAccount(int accountNumber) throws SQLException, IOException, ClassNotFoundException {

        HashMap<Long, Transaction> transactionsMap = new HashMap<>();
        Connection connection = Records.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ALL_TRANSACTIONS_BY_ACCOUNT);
        preparedStatement.setInt(1, accountNumber);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            //            int accountNumber = resultSet.getInt("accountNumber");
            long txnHash = resultSet.getLong(TXN_NUMBER);
            float amount = resultSet.getFloat(AMOUNT);
            Mode mode = Mode.valueOf(resultSet.getString(MODE));
            Timestamp createTime = resultSet.getTimestamp(TXN_TIMESTAMP);

            Transaction txn = Transaction.retrTxn(txnHash, accountNumber, amount, mode, createTime);
            transactionsMap.put(txnHash, txn);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return transactionsMap;
    }

    public static int insertTxnByFields(long txnHash, int accountNumber, float amount, Mode mode, Timestamp createTime) throws SQLException, IOException, ClassNotFoundException {
        Transaction txn = Transaction.retrTxn(txnHash,accountNumber,amount,mode,createTime);
        return insertTransaction(txn);
    }


}
