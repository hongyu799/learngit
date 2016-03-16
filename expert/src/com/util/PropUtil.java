package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropUtil {

	private static String fileName = "common.properties";

	public static String getPropValue(String key) {
		Properties prop = new Properties();
		InputStream in = PropUtil.class.getResourceAsStream("/" + fileName);
		try {
			prop.load(in);
			return prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
