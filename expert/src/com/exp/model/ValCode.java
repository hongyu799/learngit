package com.exp.model;

public class ValCode extends BaseModel {

	private String code;
	private String expName;
	private int expId;

	public String getCode() {
		return code;
	}

	public int getExpId() {
		return expId;
	}

	public void setExpId(int expId) {
		this.expId = expId;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getExpName() {
		return expName;
	}

	public void setExpName(String expName) {
		this.expName = expName;
	}

}
