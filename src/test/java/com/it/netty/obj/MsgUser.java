package com.it.netty.obj;

import java.io.Serializable;

public class MsgUser implements Serializable{

	private static final long serialVersionUID = 6735695841291650237L;

	private int userId;
	
	private String userName;
	
	private String userCode;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Override
	public String toString() {
		return "MsgUser [userId=" + userId + ", userName=" + userName
				+ ", userCode=" + userCode + "]";
	}
	
	

}
