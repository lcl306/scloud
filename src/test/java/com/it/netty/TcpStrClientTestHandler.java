package com.it.netty;

import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.CountDownLatch;

import com.it.netty.util.BufUtil;

public class TcpStrClientTestHandler extends TcpStrClientHandler {
	
	static final String OVER_FLAG = ":f:f:f";
	
	CountDownLatch countDownLatch;
	
	public TcpStrClientTestHandler(CountDownLatch countDownLatch){
		this.countDownLatch = countDownLatch;
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		this.countDownLatch.await();
	    ctx.write(BufUtil.str2buf("这是第" +Thread.currentThread().getId() +" 个客户端的请求11。"));
	    ctx.flush();
	    ctx.write(BufUtil.str2buf("这是第" +Thread.currentThread().getId() + " 个客户端的请求22。"+OVER_FLAG));
	    ctx.flush();
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx){
    	String result = BufUtil.buf2str(content);
    	System.out.println("接收服务器数据：" + result);
		ctx.close();  //如果不关闭，意味着handler一直存在
	}

}
