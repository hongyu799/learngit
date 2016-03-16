package com.util;

public class StringUtil {

	public static boolean inNull(String valStr) {
		return null == valStr || valStr.trim().length() == 0;
	}

	public static int strToInt(String val) {
		try {
			return Integer.parseInt(val);
		} catch (Exception e) {
			//e.printStackTrace();
			return 0;
		}
	}
	public static final String encodeHex(String msg) {
		byte[] bytes = null;
		try {
			bytes = msg.getBytes("GBK");
		} catch (java.io.UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		StringBuffer buff = new StringBuffer(bytes.length * 4);
		String b = "";
		char a;
		int n = 0;
		int m = 0;
		for (int i = 0; i < bytes.length; i++) {
			try {
				b = Integer.toHexString(bytes[i]);
			} catch (Exception e) {
			}
			if (bytes[i] > 0) {
				buff.append("00");
				buff.append(b);
				n = n + 1;
			} else {
				a = msg.charAt((i - n) / 2 + n);
				m = a;
				try {
					b = Integer.toHexString(m);
				} catch (Exception e) {
				}
				buff.append(b.substring(0, 4));

				i = i + 1;
			}
		}
		return buff.toString();
	}
}
