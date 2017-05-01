package com.it.netty.str;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;
import java.net.UnknownHostException;

@ChannelHandler.Sharable
public class StrServerHandler extends SimpleChannelInboundHandler<String> {
	
	 @Override
     protected void channelRead0(ChannelHandlerContext ctx, String message) throws Exception {
		System.out.println(ctx.channel().remoteAddress() + " Say : " + message);
		// 返回客户端消息 - 我已经接收到了你的消息
		ctx.writeAndFlush("你好：");
		ctx.writeAndFlush("收到你的 message !\n");
		//ctx.close();  //如果不关闭，意味着连接一直存在
     }

	@Override
	public void channelActive(ChannelHandlerContext ctx){       
	     System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");
	     try {
			ctx.writeAndFlush( "欢迎来到 " + InetAddress.getLocalHost().getHostName() + " service!\n");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} 
	}
	
}
