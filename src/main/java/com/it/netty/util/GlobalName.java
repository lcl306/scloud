package com.it.netty.util;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.Delimiters;

public class GlobalName {
	
	public static final String IP = "localhost";
	
	public static final int PORT = 83;
	
	/**如果大于这个长度还没有找到界定符，抛出TooLongFrameException*/
	public static final int MAX_FRAME_LEN = 8192;
	
	/**以\r和\n作为界定符的拆包方式
	 * 自定义可以这样写：Unpooled.copiedBuffer("&".getBytes(CODE));
	 * */
	public static final ByteBuf[] FRAME_LIMITER = Delimiters.lineDelimiter();
	
	public static final int MAX_SERVER_OBJECT_SIZE = 1024*1024;
	
	public static final int MAX_CLIENT_OBJECT_SIZE = 1024;

}
