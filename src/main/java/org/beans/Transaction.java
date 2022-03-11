package org.beans;

import org.utils.enums.Mode;

public class Transaction {
	
    long txnHash;
    int accountNumber;
    float amount;
    Mode mode;
    long createTime;
    
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
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	public Transaction(long txnHash, int accountNumber, float amount, Mode mode, long createTime) {
		super();
		this.txnHash = txnHash;
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.mode = mode;
		this.createTime = createTime;
	}
	
	public Transaction() {
		super();
	}
	
	
    
    
}
