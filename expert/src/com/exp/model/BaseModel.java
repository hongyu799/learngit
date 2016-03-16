package com.exp.model;

public class BaseModel {

	private String error;
	private String msg;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isERROR() {
		return null != msg && msg.trim().length()>0;
	}
}
