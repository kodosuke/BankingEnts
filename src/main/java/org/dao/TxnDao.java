package org.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.beans.Transaction;
import org.dao.dialects.TxnDialects;
import org.utils.DataBaseCon;
import org.enums.Mode;

public class TxnDao implements TxnDialects{

    public static int insertTransaction(Transaction transaction) throws SQLException, IOException, ClassNotFoundException, SQLException, IOException {

        Connection connection = DataBaseCon.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRANSACTION);
        preparedStatement.setLong(1, transaction.getTxnHash());
        preparedStatement.setInt(2, transaction.getAccountNumber());
        preparedStatement.setFloat(3, transaction.getAmount());
        preparedStatement.setString(4, transaction.getMode().name());
        preparedStatement.setLong(5, transaction.getCreationTime());
        int rowsModified = preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

        return rowsModified;
    }
    
    public static HashMap<Long, Transaction> getTransactionsByAccount(int accountNumber) throws SQLException, IOException, ClassNotFoundException {

        HashMap<Long, Transaction> transactionsMap = new HashMap<>();
        Connection connection = DataBaseCon.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ALL_TRANSACTIONS_BY_ACCOUNT);
        preparedStatement.setInt(1, accountNumber);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            //            int accountNumber = resultSet.getInt("accountNumber");
            long txnHash = resultSet.getLong(TXN_NUMBER);
            float amount = resultSet.getFloat(AMOUNT);
            Mode mode = Mode.valueOf(resultSet.getString(MODE));
            long createTime = resultSet.getLong(TXN_TIMESTAMP);

            Transaction txn = new Transaction(txnHash, accountNumber, amount, mode, createTime);
            transactionsMap.put(txnHash, txn);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return transactionsMap;
    }
}
