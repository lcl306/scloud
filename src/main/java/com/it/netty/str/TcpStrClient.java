package com.it.netty.str;

import java.nio.charset.Charset;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import com.it.netty.base.TcpClient;
import com.it.netty.util.BufUtil;
import com.it.netty.util.GlobalName;

public class TcpStrClient {
	
	/**
	 * 因为加入了DelimiterBasedFrameDecoder，用于解决TCP的粘包和拆包问题，
	 * server一次或多次发送的消息会被当作一个包，直至出现GlobalName.FRAME_LIMITER对应的字符后，进入channelRead和channelRead0这样的拆包后触发的方法
	 * channelReadComplete方法是每次接收报文时都会触发的方法
	 * 接收顺序：framer-->decoder-->encoder-->handler，发送顺序(ctx.writeAndFlush)：handler-->encoder-->decoder-->framer
	 * */
	public static Channel connect(final String handlerName){
		return TcpClient.connect(new ChannelInitializer<SocketChannel>() {
			
			@Override
			protected void initChannel(SocketChannel sc){
				ChannelPipeline pipeline = sc.pipeline();
				//需要与服务器对应
				pipeline.addLast("framer", new DelimiterBasedFrameDecoder(GlobalName.MAX_FRAME_LEN, GlobalName.FRAME_LIMITER));//基于分隔符的帧解码器
				pipeline.addLast("decoder", new StringDecoder(Charset.forName(BufUtil.CODE)));
				pipeline.addLast("encoder", new StringEncoder(Charset.forName(BufUtil.CODE)));
				try {
					pipeline.addLast("handler", (ChannelHandler) Class.forName(handlerName).newInstance());
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
