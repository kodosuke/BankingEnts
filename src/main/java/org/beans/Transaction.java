package org.beans;

import org.enums.Mode;

public class Transaction {
	
    long txnHash;
    int accountNumber;
    float amount;
    Mode mode;
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
	public Mode getMode() {
		return mode;
	}
	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public long getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}
	
	@Override
	public String toString() {
		return "Transaction [accountNumber=" + accountNumber + 
				", amount=" + amount + 
				", mode=" + mode +
				", creationTime=" + creationTime + "]";
	}
	public Transaction(long txnHash, int accountNumber, float amount, Mode mode, long creationTime) {
		super();
		this.txnHash = txnHash;
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.mode = mode;
		this.creationTime = creationTime;
	}

	public Transaction(int accountNumber, float amount, Mode mode, long creationTime) {
		super();
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.mode = mode;
		this.creationTime = creationTime;
		this.txnHash = this.generateHash();
	}
	public Transaction() {
		super();
	}
	
	public long generateHash() {
		
		String txnInfo = this.toString();
        long hash = 9081726354L;

        for (int i = 0; i < txnInfo.length(); i++)
            hash = hash * 31 + txnInfo.charAt(i);
        return Math.abs(hash);

	}
	
	
}