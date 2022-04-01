package org.code.models;

import org.code.enums.TransactionMode;

public class Transaction {

    long txnHash;
    int accountNumber;
    float amount;
    TransactionMode mode;
    long creationTime;
    float closingBalance;
    String description;
    
    

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(float closingBalance) {
		this.closingBalance = closingBalance;
	}

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

    public Transaction(int accountNumber, float amount, TransactionMode mode, long creationTime, float closingBalance, String description) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.mode = mode;
        this.creationTime = creationTime;
        this.closingBalance = closingBalance;
        this.description = description;
        this.txnHash = this.generateTransactionHash();
    }

    public Transaction(long txnHash, int accountNumber, float amount, TransactionMode mode, long creationTime, float closingBalance, String description) {
        this.txnHash = txnHash;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.mode = mode;
        this.creationTime = creationTime;
        this.closingBalance = closingBalance;
        this.description = description;
    }

    public long generateTransactionHash() {

        String txnInfo = this.getData();
        long hash = 9081726354L;

        for (int i = 0; i < txnInfo.length(); i++)
            hash = hash * 31 + txnInfo.charAt(i);
        return Math.abs(hash);

    }


    public String getData() {
        return "Transaction [accountNumber=" + accountNumber +
                ", amount=" + amount +
                ", mode=" + mode +
                ", creationTime=" + creationTime + 
                ", closingBalance=" + closingBalance + 
                ", description=" + description +
                "]";
    }
}
