package com.it.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class StringUtil {
	
	public static final String CODE = "UTF-8";
	
	public static String buf2str(ByteBuf buf){
		return Charset.forName(CODE).decode(buf.nioBuffer()).toString();
		/*StringBuilder builder = new StringBuilder();
        while(buf.isReadable()) {
            builder.append((char)buf.readByte());
        }
        return builder.toString();*/
		/*byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);
		try {
			return new String(bytes, CODE);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}*/
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
