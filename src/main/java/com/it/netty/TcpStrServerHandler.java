package com.it.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TcpStrServerHandler extends ChannelInboundHandlerAdapter {
	
	static final String ATTR_KEY_NAME = "content";
	
	static final String OVER_FLAG = ":f:f:f";
	
	private static Log logger = LogFactory.getLog(TcpStrServerHandler.class);
	
	 /**
     * 每一个channel，都有独立的handler、ChannelHandlerContext、ChannelPipeline、Attribute
     * 所以不需要担心多个channel中的这些对象相互影响。<br>
     * 这里我们使用contentKey这个key，记录这个handler中已经接收到的客户端信息。
     */
	private static AttributeKey<ByteBuf> contentKey = AttributeKey.valueOf(ATTR_KEY_NAME);

	@Override
	public void channelRegistered(ChannelHandlerContext ctx){
		TcpStrServerHandler.logger.info("super.channelRegistered(ctx)="+ctx.toString());
	}
	
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx){
		TcpStrServerHandler.logger.info("super.channelUnregistered(ctx)="+ctx.toString());
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx){
		TcpStrServerHandler.logger.info("super.channelActive(ctx)="+ctx.toString());
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx){
		TcpStrServerHandler.logger.info("super.channelInactive(ctx)="+ctx.toString());
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg){
		TcpStrServerHandler.logger.info("super.channelRead(ctx, msg)="+ctx.toString());
		//read只负责接收和累加数据，不做处理, 因为前面的handler是ByteArrayEncoder，所以msg是ByteBuf类型
		ByteBuf buf = (ByteBuf)msg;
		try {
			ByteBuf content = getContent(ctx);
			content.writeBytes(buf);
			/*while(buf.isReadable()){
				
			}*/
		} finally {
			buf.release();
		}
	}
	
	private ByteBuf getContent(ChannelHandlerContext ctx){
		ByteBuf buf = ctx.channel().attr(contentKey).get();
		if(buf==null){
			buf = ctx.alloc().buffer();
			ctx.channel().attr(contentKey).set(buf);
		}
		return buf;
	}
	
	private void clearContent(ChannelHandlerContext ctx){
		ctx.channel().attr(contentKey).set(ctx.alloc().buffer());
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx){
		TcpStrServerHandler.logger.info("super.channelReadComplete(ChannelHandlerContext ctx)="+ctx.toString());
		String contentStr = StringUtil.buf2str(getContent(ctx));
		if(contentStr.indexOf(OVER_FLAG)==-1){  //说明还没完
			return;
		}
		clearContent(ctx);
		ctx.write(StringUtil.str2buf(contentStr));
		ctx.flush();
	    //ctx.writeAndFlush(contentStr);
		/*ByteBuf buf = ctx.alloc().buffer(1024);
		buf.writeBytes(contentStr.getBytes());
		ctx.writeAndFlush(buf);*/
		//ctx.close();  //如果不关闭，意味着handler一直存在
	}
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt){
		 TcpStrServerHandler.logger.info("super.userEventTriggered(ctx, evt)="+ctx.toString());
	}
	
	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx){
		TcpStrServerHandler.logger.info("super.channelWritabilityChanged(ctx)="+ctx.toString());
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		TcpStrServerHandler.logger.info("super.exceptionCaught(ctx, cause)="+ctx.toString());
        //关闭通道意味着TCP将正常断开，其中所有的handler、ChannelHandlerContext、ChannelPipeline、Attribute等信息都将注销
		cause.printStackTrace();
		ctx.close();
	}
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx){
		TcpStrServerHandler.logger.info("super.handlerAdded(ctx)="+ctx.toString());
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx){
		TcpStrServerHandler.logger.info("super.handlerRemoved(ctx)="+ctx.toString());
	}
}
