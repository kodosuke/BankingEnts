package org.codes.dao;

import org.codes.beans.Transaction;
import org.codes.dao.interfaces.TxnDialects;
import org.codes.utils.Mode;
import org.codes.utils.Records;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class TxnDAO implements TxnDialects {

    public static HashMap<Long, Transaction> retrAllTransactions() throws SQLException, IOException, ClassNotFoundException {

        HashMap<Long, Transaction> transactionsMap = new HashMap<>();
        Connection connection = Records.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ALL_TRANSACTIONS);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            long txnHash = resultSet.getLong("txnHash");
            int accountNumber = resultSet.getInt("accountNumber");
            float amount = resultSet.getFloat("amount");
            Mode mode = Mode.valueOf(resultSet.getString("mode"));
            Timestamp createTime = resultSet.getTimestamp("createTime");

            Transaction txn = Transaction.retrTxn(txnHash, accountNumber, amount, mode, createTime);
            transactionsMap.put(txnHash, txn);
        }
        return transactionsMap;
    }
}
