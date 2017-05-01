package com.it.netty.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class BufUtil {
	
	public static final String CODE = "UTF-8";
	
	public static byte[] buf2bytes(ByteBuf buf){
		byte[] dst = null;
		if(buf.isReadable()){
			dst = buf2bytes(buf, buf.readableBytes());
		}
		return dst;
	}
	
	public static byte[] buf2bytes(ByteBuf buf, int len){
		byte[] dst = null;
		if(buf.isReadable()){
			dst = new byte[len];
			buf.readBytes(dst);
		}
		return dst;
	}
	
	public static void bytes2buf(ByteBuf out, byte[] src){
		if(out!=null && out.isWritable()){
			out.writeBytes(src);
		}
	}
	
	public static String buf2str(ByteBuf buf){
		return Charset.forName(CODE).decode(buf.nioBuffer()).toString();
	}
	
	public static ByteBuf str2buf(String str){
		try {
			return Unpooled.wrappedBuffer(str.getBytes(CODE));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

}
