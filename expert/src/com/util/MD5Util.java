package com.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("unused")
public class MD5Util {
	// 全局数组
	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	private final static String SEED = "gmtstroff";

	public MD5Util() {
	}

	// 返回形式为数字跟字符串
	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		// System.out.println("iRet="+iRet);
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	// 返回形式只为数字
	private static String byteToNum(byte bByte) {
		int iRet = bByte;
		System.out.println("iRet1=" + iRet);
		if (iRet < 0) {
			iRet += 256;
		}
		return String.valueOf(iRet);
	}

	// 转换字节数组为16进制字串
	private static String byteToString(byte[] bByte) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}

	public static String GetBaseMD5Code(String strObj) {
		System.out.println("一次加密原文==>" + strObj);
		String secondRssultString = null;
		try {
			secondRssultString = new String(strObj);
			MessageDigest md = MessageDigest.getInstance("MD5");
			String resultString = byteToString(md.digest(strObj.getBytes()));
			System.out.println("一次加密结果==>" + resultString);
			return resultString.toUpperCase();
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String GetMD5Code(String strObj) {
		System.out.println("一次加密原文==>" + strObj);
		String secondRssultString = null;
		try {
			secondRssultString = new String(strObj);
			MessageDigest md = MessageDigest.getInstance("MD5");
			// md.digest() 该函数返回值为存放哈希值结果的byte数组
			String resultString = byteToString(md.digest(strObj.getBytes()));
			System.out.println("一次加密结果==>" + resultString);
			String secondString = SEED + resultString + SEED;
			System.out.println("二次加密原文==>" + secondString);
			secondRssultString = byteToString(md.digest(secondString.getBytes()));
			System.out.println("二次加密结果==>" + secondRssultString);
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return secondRssultString;
	}

	public static void main(String[] args) {
		System.out.println(MD5Util.GetBaseMD5Code("trsadmin"));
	}
}
