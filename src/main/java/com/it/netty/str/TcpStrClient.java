package com.it.netty.str;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import com.it.netty.base.GlobalName;
import com.it.netty.base.TcpClient;

public class TcpStrClient {
	
	/**
	 * 因为加入了DelimiterBasedFrameDecoder，所以server发出的消息必须以\n结尾，否则只能进入channelReadComplete方法，不能进入channelRead和channelRead0方法
	 * */
	public static Channel connect(final String handlerName){
		return TcpClient.connect(new ChannelInitializer<SocketChannel>() {
			
			@Override
			protected void initChannel(SocketChannel sc){
				ChannelPipeline pipeline = sc.pipeline();
				//需要与服务器对应
				//基于分隔符的帧解码器
				pipeline.addLast("framer", new DelimiterBasedFrameDecoder(GlobalName.MAX_FRAME_LEN, Delimiters.lineDelimiter()));
				pipeline.addLast("decoder", new StringDecoder());
				pipeline.addLast("encoder", new StringEncoder());
				try {
					pipeline.addLast("handler", (ChannelHandler) Class.forName(handlerName).newInstance());
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
