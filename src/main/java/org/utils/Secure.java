package org.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Secure {

	public static String generateHash(String password) throws NoSuchAlgorithmException {
		
		String pwdHash;
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] pwdBytes = md.digest();
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < pwdBytes.length; i++) {
			stringBuilder.append(Integer.toString((pwdBytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		
		pwdHash= stringBuilder.toString();
		
		return pwdHash;
	}
}
