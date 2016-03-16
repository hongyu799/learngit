package com.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.exp.model.PhoneCode;

public class DataUtil {

	private static Map<String, PhoneCode> codes = new HashMap<String, PhoneCode>();

	public static PhoneCode getCodeByPhone(String mobile) {
		return codes.get(mobile);
	}

	public static void setPhoneCode(String mobile, String code) {
		PhoneCode pc = new PhoneCode();
		pc.setPhone(mobile);
		pc.setVcode(code);
		pc.setTime(new Date());
		codes.put(mobile, pc);
	}
}
