package com.uxb2b.ecp.service;

import java.security.MessageDigest;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 
 * @author steve
 *
 */
@Service
public class AlgorithmService {

	private Logger log = LoggerFactory.getLogger(AlgorithmService.class);
	
	/**
	 * 密碼MD5加密
	 * 
	 * @param pwd 密碼明文 
	 * @return 加密過的密碼
	 */
	public String toEncryptMD5(String pwd) {
		StringBuffer sbMD5 = new StringBuffer();
		try {

			MessageDigest mDigest = MessageDigest.getInstance("MD5");
			byte[] tempMD5 = mDigest.digest(pwd.getBytes());
			IntStream.range(0, tempMD5.length).forEach(i -> sbMD5.append("" + Integer.toHexString(tempMD5[i] & 0xFF)));
			
		} catch (Exception e) {
			
			e.printStackTrace();
			log.error("MD5編碼失敗");
		}
		return sbMD5.toString();
	}
}
