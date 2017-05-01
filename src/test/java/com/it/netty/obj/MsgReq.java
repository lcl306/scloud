package com.it.netty.obj;

import java.io.Serializable;
import java.util.Date;

public class MsgReq implements Serializable{
	
	private static final long serialVersionUID = -4889028947436242735L;

	private MsgUser user;
	
	private long reqId;
	
	private Date reqDate;
	
	private String reqInfo;
	
	private int reqCode;

	public int getReqCode() {
		return reqCode;
	}

	public void setReqCode(int reqCode) {
		this.reqCode = reqCode;
	}

	public MsgUser getUser() {
		return user;
	}

	public void setUser(MsgUser user) {
		this.user = user;
	}

	public long getReqId() {
		return reqId;
	}

	public void setReqId(long reqId) {
		this.reqId = reqId;
	}

	public Date getReqDate() {
		return reqDate;
	}

	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}

	public String getReqInfo() {
		return reqInfo;
	}

	public void setReqInfo(String reqInfo) {
		this.reqInfo = reqInfo;
	}

	@Override
	public String toString() {
		return "MsgReq [user=" + user.toString() + ", reqId=" + reqId + ", reqDate="
				+ reqDate + ", reqInfo=" + reqInfo + ", reqCode=" + reqCode
				+ "]";
	}

}
