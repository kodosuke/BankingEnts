package org.codes.utils;

import java.time.LocalDateTime;

public class HashCode {

    public static long generateHash(int accountNumber, float amount, Mode mode) {
        String transactionDetails = "{ accountNumber : " + accountNumber + ", amount : " + amount + ", mode : " +
                mode.name() + ", currentTime : " + LocalDateTime.now();

        long hash = 9081726354L;

        for (int i = 0; i < transactionDetails.length(); i++)
            hash = hash * 31 + transactionDetails.charAt(i);
        return Math.abs(hash);
    }
}
