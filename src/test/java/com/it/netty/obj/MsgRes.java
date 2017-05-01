package com.it.netty.obj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MsgRes implements Serializable{
	
	private static final long serialVersionUID = -2223550950743463106L;

	private MsgUser user;
	
	private List<MsgTicket> tickets = new ArrayList<>();
	
	private long resId;
	
	private Date resDate;
	
	private String resInfo;
	
	private int resCode;
	
	public int getResCode() {
		return resCode;
	}

	public void setResCode(int resCode) {
		this.resCode = resCode;
	}

	private int ticketNum;
	
	public int getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(int ticketNum) {
		this.ticketNum = ticketNum;
	}

	public MsgUser getUser() {
		return user;
	}

	public void setUser(MsgUser user) {
		this.user = user;
	}

	public List<MsgTicket> getTickets() {
		return tickets;
	}

	public void setTickets(List<MsgTicket> tickets) {
		this.tickets = tickets;
	}

	public long getResId() {
		return resId;
	}

	public void setResId(long resId) {
		this.resId = resId;
	}

	public Date getResDate() {
		return resDate;
	}

	public void setResDate(Date resDate) {
		this.resDate = resDate;
	}

	public String getResInfo() {
		return resInfo;
	}

	public void setResInfo(String resInfo) {
		this.resInfo = resInfo;
	}

	@Override
	public String toString() {
		String ti = "[";
		for(MsgTicket t : tickets){
			ti += t.toString()+", ";
		}
		ti += "]";
		return "MsgRes [user=" + user.toString() + ", tickets=" + ti + ", resId="
				+ resId + ", resDate=" + resDate + ", resInfo=" + resInfo
				+ ", resCode=" + resCode + ", ticketNum=" + ticketNum + "]";
	}

}
