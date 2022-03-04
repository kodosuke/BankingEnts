package org.codes.beans;

import org.codes.utils.Mode;
import java.sql.Timestamp;

public class Transaction {

    long txnHash;
    int accountNumber;
    float amount;
    Mode mode;

    public long getTxnHash() {
        return txnHash;
    }

    public void setTxnHash(long txnHash) {
        this.txnHash = txnHash;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public static Transaction retrTxn(long txnHash, int accountNumber, float amount, Mode mode, Timestamp createTime) {

        Transaction txn = new Transaction();

        txn.setTxnHash(txnHash);
        txn.setAccountNumber(accountNumber);
        txn.setAmount(amount);
        txn.setMode(mode);
        txn.setCreateTime(createTime);
        return txn;
    }

    Timestamp createTime;

}
