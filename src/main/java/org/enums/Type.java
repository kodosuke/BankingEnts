package org.enums;

public enum Type { 
	
	SAVINGS(100), 
	CURRENT(0);
	
	public final float balance;
	
	Type(float balance) {
		this.balance = balance;
	}
}
