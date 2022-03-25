package org.code.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordSecurity {

    public static String generateHash(String password) throws NoSuchAlgorithmException {

        String pwdHash;

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] pwdBytes = md.digest();
        StringBuilder stringBuilder = new StringBuilder();
        for (byte pwdByte : pwdBytes) {
            stringBuilder.append(Integer.toString((pwdByte & 0xff) + 0x100, 16).substring(1));
        }

        pwdHash= stringBuilder.toString();

        return pwdHash;
    }

}
