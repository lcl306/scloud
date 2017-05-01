package com.it.netty.obj;

import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

import com.it.netty.base.BaseHandlerAdapter;

public class ObjClientHandler extends BaseHandlerAdapter {
	
	@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	super.channelActive(ctx);
    	ctx.writeAndFlush(create(1));
    }
	
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    	MsgRes res = (MsgRes)msg;
    	if(res.getResCode()==1){  //查询返回
    		if(res.getTicketNum()>0){
    			ctx.writeAndFlush(create(2));
    		}
    	}else if(res.getResCode()==2){  //订票返回
    		System.out.println(res.toString());
    	}
    }
	
	private MsgReq create(int code){
		MsgReq req = new MsgReq();
		req.setReqCode(code);
		req.setReqDate(new Date());
		if(code==1){
			req.setReqId(100l);
			req.setReqInfo("查询余票");
		}else if(code==2){
			req.setReqId(200l);
			req.setReqInfo("订票");
		}
		MsgUser user = new MsgUser();
		req.setUser(user);
		user.setUserCode("239049043");
		user.setUserId(1);
		user.setUserName("张三");
		return req;
	}

}
