package com.it.netty.lenfield;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import com.it.netty.util.ImageUtil;

public class LenFieldServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		MsgText msg = MsgText.trans(in);
		System.out.println(msg.toString());
		ImageUtil.bytes2file("D:/360安全浏览器下载", "文件.jpg", msg.getImage());
		msg.setImage(null);
		ctx.writeAndFlush(msg);
	}

}
