package com.it.netty.lenfield;

import java.nio.charset.Charset;

import com.it.netty.util.BufUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MsgTextEncoder extends MessageToByteEncoder<MsgText> {

	@Override
	protected void encode(ChannelHandlerContext ctx, MsgText msg, ByteBuf out) throws Exception {
		if(msg==null) throw new Exception("msg is null");
		out.writeByte(msg.getType());
		out.writeByte(msg.getFlag());
		String body = msg.getBody();
		int len = 8; //8是bodybs的length和imagedelength
		int bodylen = 0;
		byte[] bodybs = null;
		if(body!=null){
			bodybs = msg.getBody().getBytes(Charset.forName(BufUtil.CODE));
			bodylen = bodybs.length;
			len += bodylen;
		}
		byte[] image = msg.getImage();
		int imagelen = 0;
		if(image!=null){
			imagelen = image.length;
			len += imagelen;
		}
		out.writeInt(len);
		out.writeInt(bodylen);
		out.writeInt(imagelen);
		if(bodybs!=null){
			BufUtil.bytes2buf(out, bodybs);
		}
		if(image!=null){
			BufUtil.bytes2buf(out, image);
		}
	}

}
