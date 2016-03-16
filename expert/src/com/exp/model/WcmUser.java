package com.exp.model;

public class WcmUser extends BaseModel {

	private String userId;
	private String userName;
	private String password;
	private String nickName;
	private int status;
	private int ugroup;

	public int getUgroup() {
		return ugroup;
	}

	public void setUgroup(int ugroup) {
		this.ugroup = ugroup;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
