package com.it.netty.str;

import java.nio.charset.Charset;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import com.it.netty.base.TcpServer;
import com.it.netty.util.BufUtil;
import com.it.netty.util.GlobalName;

public class TcpStrServer {
	
	/**
	 * 因为该server只有一个自定义的handler，所以该handler必须注解为@ChannelHandler.Sharable
	 * 这样该handler处理所有的客户端请求，这样的handler不是线程安全的
	 * 因为加入了DelimiterBasedFrameDecoder，用于解决TCP的粘包和拆包问题，
	 * client一次或多次发送的消息会被当作一个包，直至出现GlobalName.FRAME_LIMITER对应的字符后，进入channelRead和channelRead0这样的拆包后触发的方法
	 * channelReadComplete方法是每次接收报文时都会触发的方法
	 * ctx.fireChannelRead(obj);发送到下一个channelHandler, ctx.writeAndFlush(obj);返回到上一个channelHandler
	 * 接收顺序：framer-->decoder-->encoder-->handler，发送顺序(ctx.writeAndFlush)：handler-->encoder-->decoder-->framer
	 * */
	public static Channel start(final ChannelHandler handler){
		return TcpServer.start(new ChannelInitializer<NioSocketChannel>() {
			@Override
			protected void initChannel(NioSocketChannel nc) throws Exception {
				ChannelPipeline pipeline = nc.pipeline();
				//第一个参数是单条消息的最大长度，如果大于这个长度还没有找到第二个参数指定的分隔符，抛出TooLongFrameException
				pipeline.addLast("framer", new DelimiterBasedFrameDecoder(GlobalName.MAX_FRAME_LEN, GlobalName.FRAME_LIMITER)); 
				// 字符串解码 和 编码
				pipeline.addLast("decoder", new StringDecoder(Charset.forName(BufUtil.CODE)));
				pipeline.addLast("encoder", new StringEncoder(Charset.forName(BufUtil.CODE)));
				pipeline.addLast("handler", handler);
			}
		});
	}

}
