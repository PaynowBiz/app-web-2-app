package com.tosspayments.paynowbiz.util;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
	private final Key keySpec;
	/**
	 * @category 생성자 
	 * @param    암호화키 
	 * @exception
	 */
	public AESUtil(String key) throws Exception {
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

		this.keySpec = keySpec;
	}

	/**
	 * @category 암호화 
	 * @param    암호화 할 문자 
	 * @return   암호화 된 문자 
	 * @exception
	 */
	public String encryptEcb(String str) throws Exception {
		Cipher c = Cipher.getInstance("AES/ECB/PKCS5PADDING");
		c.init(Cipher.ENCRYPT_MODE, keySpec);
		byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
		
		return new String(Base64.getEncoder().encode(encrypted));
	}
}