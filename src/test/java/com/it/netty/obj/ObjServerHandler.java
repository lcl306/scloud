package com.it.netty.obj;

import io.netty.channel.ChannelHandlerContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.it.netty.base.BaseHandlerAdapter;

public class ObjServerHandler extends BaseHandlerAdapter {
	
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    	super.channelRead(ctx, msg);
		MsgReq req = (MsgReq)msg;
		System.out.println(req.toString());
    	if(req.getReqCode()==1){  ////查询
    		ctx.writeAndFlush(echo(req));
    	}else if(req.getReqCode()==2){  //订票
    		ctx.writeAndFlush(order(req));
    	}
    }
	
	private MsgRes order(MsgReq req){
		MsgRes res = new MsgRes();
		res.setResId(202l);
		res.setResDate(new Date());
		res.setResInfo("返回订票信息");
		res.setUser(req.getUser());
		res.setTicketNum(2);
		res.setResCode(2);
		List<MsgTicket> ts = new ArrayList<MsgTicket>();
		res.setTickets(ts);
		MsgTicket t = new MsgTicket();
		t.setInfo("第一张票");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
		try {
			t.setOrderDate(sdf.parse("20170502"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		t.setTicketId(1l);
		ts.add(t);
		MsgTicket t2 = new MsgTicket();
		t2.setInfo("第2张票"); 
		try {
			t2.setOrderDate(sdf.parse("20170505"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		t2.setTicketId(2l);
		ts.add(t2);
		return res;
	}
	
	private MsgRes echo(MsgReq req){
		MsgRes res = new MsgRes();
		res.setResId(201l);
		res.setResDate(new Date());
		res.setResInfo("返回车票数");
		res.setUser(req.getUser());
		res.setTicketNum(2);
		res.setResCode(1);
		return res;
	}

}
