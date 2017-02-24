package com.one.String;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
  * @Package com.one.String 
  * @ClassName Secret.java
  * @author jun.wu  
  * @date 2017年2月20日 上午11:00:29 
  * @Description: SHA 加密
 */
public class Secret {

	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		String password = "123456";
		byte[] plaintext = password.getBytes("UTF-8");
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
		messageDigest.update(plaintext);
		byte[] digest = messageDigest.digest();
		System.out.println("----message digest----");
		for(int i = 0; i < digest.length; i++){
			System.out.printf("%x", digest[i]);
			System.out.print(" ");
		}
		
	}

}
