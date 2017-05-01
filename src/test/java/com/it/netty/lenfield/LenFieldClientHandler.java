package com.it.netty.lenfield;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import com.it.netty.base.BaseHandlerAdapter;
import com.it.netty.util.ImageUtil;

public class LenFieldClientHandler extends BaseHandlerAdapter {
	
	@Override  
    public void channelActive(ChannelHandlerContext ctx) throws Exception {  
		super.channelActive(ctx);
        MsgText text = new MsgText();
        text.setType((byte)0xAB);
        text.setFlag((byte)0xCD);
        text.setBody("测试内容3334aaaa3如 ");
        text.setImage(ImageUtil.file2bytes("C:/Users/Public/Pictures/Sample Pictures/Penguins.jpg"));
        ctx.writeAndFlush(text);  
    }  
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
		ByteBuf in = (ByteBuf)msg;
		MsgText text = MsgText.trans(in);
		System.out.println(text);
		ctx.close();
	}

}
