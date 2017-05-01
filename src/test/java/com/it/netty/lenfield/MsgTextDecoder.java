package com.it.netty.lenfield;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import com.it.netty.util.BufUtil;

public class MsgTextDecoder extends LengthFieldBasedFrameDecoder {
	
	private static final int HEAD_SIZE = 6; //byte+byte+int = 1+1+4 = 6

	
	public MsgTextDecoder(int maxFrameLength, int lengthFieldOffset,int lengthFieldLength, 
			int lengthAdjustment, int initialBytesToStrip) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment,initialBytesToStrip);
	}
	
	public MsgTextDecoder(){
		super(Integer.MAX_VALUE, 2, 4, 0, 0);
	}

	@Override
	public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception{
		if(in==null) return null;
		if(in.readableBytes()<HEAD_SIZE) throw new Exception("可读信息段比头部信息小。");
		MsgText text = new MsgText();
		text.setType(in.readByte());
		text.setFlag(in.readByte());
		int length = in.readInt();
		text.setLength(length);
		if(in.readableBytes()<length) throw new Exception("body的实际长度小于length");
		int bodylen = in.readInt();
		int imagelen = in.readInt();
		if(bodylen>0) text.setBody(new String(BufUtil.buf2bytes(in, bodylen), BufUtil.CODE));
		if(imagelen>0) text.setImage(BufUtil.buf2bytes(in, imagelen));
		return text;
	}

}
