package com.it.netty.str;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import com.it.netty.base.GlobalName;
import com.it.netty.base.TcpServer;

public class TcpStrServer {
	
	/**
	 * 因为该server只有一个自定义的handler，所以该handler必须注解为@ChannelHandler.Sharable
	 * 这样该handler处理所有的客户端请求，这样的handler不是线程安全的
	 * 因为加入了DelimiterBasedFrameDecoder，所以client发出的消息必须以\n结尾，否则只能进入channelReadComplete方法，不能进入channelRead和channelRead0方法
	 * */
	public static Channel start(final ChannelHandler handler){
		return TcpServer.start(new ChannelInitializer<NioSocketChannel>() {
			@Override
			protected void initChannel(NioSocketChannel nc) throws Exception {
				ChannelPipeline pipeline = nc.pipeline();
				// 以("\n")为结尾分割的 解码器
				pipeline.addLast("framer", new DelimiterBasedFrameDecoder(GlobalName.MAX_FRAME_LEN, Delimiters.lineDelimiter())); 
				// 字符串解码 和 编码
				pipeline.addLast("decoder", new StringDecoder());
				pipeline.addLast("encoder", new StringEncoder());
				pipeline.addLast("handler", handler);
			}
		});
	}

}
