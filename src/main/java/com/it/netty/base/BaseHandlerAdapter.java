package com.it.netty.base;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseHandlerAdapter extends ChannelInboundHandlerAdapter {
	
	private static Log logger = LogFactory.getLog(BaseHandlerAdapter.class);
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx){
		logger.info("super.handlerAdded(ctx)="+ctx.toString());
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx){
		logger.info("super.handlerRemoved(ctx)="+ctx.toString());
	}
	
	@Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		logger.info("super.channelRegistered(ChannelHandlerContext ctx)="+ctx.toString());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
    	logger.info("super.channelUnregistered(ChannelHandlerContext ctx)="+ctx.toString());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    	logger.info("super.channelInactive(ChannelHandlerContext ctx)="+ctx.toString());
    }

    // 连接成功后触发
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	logger.info("super.channelActive(ChannelHandlerContext ctx)="+ctx.toString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    	logger.info("super.channelRead(ChannelHandlerContext ctx, msg)="+ctx.toString());
    }
    
    /**
     * 每一次接收消息，都会触发readComplete
     * */
    @Override
	public void channelReadComplete(ChannelHandlerContext ctx){
    	logger.info("super.channelReadComplete(ChannelHandlerContext ctx)="+ctx.toString());
		//ctx.close();  //如果不关闭，意味着handler一直存在
	}

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	logger.info("super.exceptionCaught(ChannelHandlerContext ctx, cause)="+ctx.toString());
        cause.printStackTrace();
        ctx.close();
    }
    
    @Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt){
		 logger.info("super.userEventTriggered(ctx, evt)="+ctx.toString());
	}
	
	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx){
		logger.info("super.channelWritabilityChanged(ctx)="+ctx.toString());
	}

}
