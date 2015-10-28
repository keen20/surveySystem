package com.survey.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class DataUtil {
	private DataUtil() {}
	public static String md5(String str){
		try {
			char[] chars={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
			StringBuffer sb = new StringBuffer();
			byte[] bytes = str.getBytes();//编程字节数组
			//获取md5分解后的16位字节数组
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] tags = md.digest(bytes);
			//把每个字节都分解为两个十六进制字符
			for(byte t:tags){
				sb.append(chars[(t>>4) & 0x0f]);
				sb.append(chars[t & 0x0f]);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
