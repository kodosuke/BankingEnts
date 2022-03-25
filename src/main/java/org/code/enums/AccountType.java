package org.code.enums;

public enum AccountType {

    SAVINGS (1000),
    CURRENT(0);

    public final float minBalance;

    AccountType(float minBalance){
        this.minBalance = minBalance;
    }
}
