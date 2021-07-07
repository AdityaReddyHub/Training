package com.bank.reigister.customer.util;

import java.util.Random;

public class Util {
	
	public static String generateRandomPassword(int len) {
		//String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%&";
		String chars = "ADI123@$";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}
	
	public static String generateAccountNumber() {
		StringBuilder accountNumber = new StringBuilder();
	    Random random = new Random();
	    for(int i = 0; i < 11; i++) {
	    	accountNumber.append(random.nextInt(10));
	    }
	   String accountNumber1 = "ICICI"+accountNumber.toString();
	    return accountNumber1;
	}
}
