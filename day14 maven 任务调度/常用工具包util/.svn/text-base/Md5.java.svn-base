package com.etoak.util;
import java.security.MessageDigest;

public class Md5 {
	public final static String calcMD5(String ss) {
		String s = ss == null ? "" : ss;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);

		} catch (Exception e) {
			return null;
		}
	}
	public static void main(String[]args){
		String str = Md5.calcMD5("123456");
		System.out.println(str);
	}

}
