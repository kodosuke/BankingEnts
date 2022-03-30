package org.code.models;

import org.code.enums.TransactionMode;

public class Transaction {

    long txnHash;
    int accountNumber;
    float amount;
    TransactionMode mode;
    long creationTime;

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

    public TransactionMode getMode() {
        return mode;
    }

    public void setMode(TransactionMode mode) {
        this.mode = mode;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public Transaction() {
    }

    public Transaction(int accountNumber, float amount, TransactionMode mode, long creationTime) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.mode = mode;
        this.creationTime = creationTime;
        this.txnHash = this.generateTransactionHash();
    }

    public Transaction(long txnHash, int accountNumber, float amount, TransactionMode mode, long creationTime) {
        this.txnHash = txnHash;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.mode = mode;
        this.creationTime = creationTime;
    }

    public long generateTransactionHash() {

        String txnInfo = this.toString();
        long hash = 9081726354L;

        for (int i = 0; i < txnInfo.length(); i++)
            hash = hash * 31 + txnInfo.charAt(i);
        return Math.abs(hash);

    }

    @Override
    public String toString() {
        return "Transaction [accountNumber=" + accountNumber +
                ", amount=" + amount +
                ", mode=" + mode +
                ", creationTime=" + creationTime + "]";
    }
}
