package com.it.netty.str;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class StrClientHandler extends SimpleChannelInboundHandler<String> {

	boolean send= false;
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println("Server say : " + msg);
		if(!send){
			//服务端拆包时并不是按照发送次数，而是看有没有\n，所以这2条消息会并为1条
			ctx.writeAndFlush("1.测试str的异步=");
			ctx.writeAndFlush("2.测试str的异步\n");
			send = true;
		}
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
	    System.out.println("Client active ");
	    //ctx.fireChannelRead(obj);发送到下一个channelHandler
	    //ctx.writeAndFlush(obj);返回到上一个channelHandler
	    ctx.writeAndFlush("1.连接激活后发送\n");
		ctx.writeAndFlush("2.连接激活后发送\n");
	    super.channelActive(ctx);
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
	    System.out.println("Client close ");
	    super.channelInactive(ctx);
	}

}
