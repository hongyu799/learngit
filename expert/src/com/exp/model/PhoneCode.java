package com.exp.model;

import java.util.Date;

public class PhoneCode {

	private String phone;
	private String vcode;
	private Date time;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public boolean isTimeOut() {
		if (time != null
				&& time.getTime() > (new Date().getTime() - 30 * 60 * 1000))
			return false;
		else
			return true;

	}
}
