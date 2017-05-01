package com.it.netty.obj;

import java.io.Serializable;
import java.util.Date;


public class MsgTicket implements Serializable{
	
	private static final long serialVersionUID = -8333598867877089569L;

	private long ticketId;
	
	private Date orderDate;
	
	private String info;

	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "MsgTicket [ticketId=" + ticketId + ", orderDate=" + orderDate
				+ ", info=" + info + "]";
	}
	
	

}
