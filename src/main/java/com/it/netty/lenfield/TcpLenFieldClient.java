package com.it.netty.lenfield;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import com.it.netty.base.TcpClient;

public class TcpLenFieldClient {
	
	public static <E extends MessageToByteEncoder<?>, T extends ChannelHandler> 
	Channel connect(final LenFieldDto lenFieldDto, final Class<E> lenFieldEnClass, final Class<T> handlerClass){
		return TcpClient.connect(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel sc){
				ChannelPipeline pipeline = sc.pipeline();
				try {
					pipeline.addLast("decoder", new LengthFieldBasedFrameDecoder(
							lenFieldDto.getMaxFrameLength(), lenFieldDto.getLengthFieldOffset(), lenFieldDto.getLengthFieldLength(),
							lenFieldDto.getLengthAdjustment(), lenFieldDto.getInitialBytesToStrip()));
					pipeline.addLast("encoder", lenFieldEnClass.newInstance());
					pipeline.addLast("handler", handlerClass.newInstance());
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
