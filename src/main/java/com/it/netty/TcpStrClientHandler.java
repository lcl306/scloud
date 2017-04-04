package com.it.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TcpStrClientHandler extends ChannelInboundHandlerAdapter {
	
	protected ByteBuf content = Unpooled.buffer();
	
	private static Log logger = LogFactory.getLog(TcpStrClientHandler.class);
	
	@Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		TcpStrClientHandler.logger.info("super.channelRegistered(ChannelHandlerContext ctx)="+ctx.toString());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
    	TcpStrClientHandler.logger.info("super.channelUnregistered(ChannelHandlerContext ctx)="+ctx.toString());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    	TcpStrClientHandler.logger.info("super.channelInactive(ChannelHandlerContext ctx)="+ctx.toString());
    }

    // 连接成功后，向server发送消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	TcpStrClientHandler.logger.info("super.channelActive(ChannelHandlerContext ctx)="+ctx.toString());
    	//ctx.write(ByteBuf);
    	//ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    	TcpStrClientHandler.logger.info("super.channelRead(ChannelHandlerContext ctx, msg)="+ctx.toString());
    	ByteBuf buf = (ByteBuf)msg;
		try {
			content.writeBytes(buf);
		} finally {
			buf.release();
		}
    }
    
    @Override
	public void channelReadComplete(ChannelHandlerContext ctx){
    	TcpStrClientHandler.logger.info("super.channelReadComplete(ChannelHandlerContext ctx)="+ctx.toString());
    	//String result = StringUtil.buf2str(content);
		//ctx.close();  //如果不关闭，意味着handler一直存在
	}

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	TcpStrClientHandler.logger.info("super.exceptionCaught(ChannelHandlerContext ctx, cause)="+ctx.toString());
        cause.printStackTrace();
        ctx.close();
    }
}

