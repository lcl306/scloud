package com.it.netty.lenfield;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import com.it.netty.base.TcpServer;

public class TcpLenFieldServer {
	
	public static <E extends MessageToByteEncoder<?>, T extends ChannelHandler>
	Channel start(final LenFieldDto lenFieldDto, final Class<E> lenFieldEnClass, final Class<T> handlerClass){
		return TcpServer.start(new ChannelInitializer<NioSocketChannel>() {
			@Override
			protected void initChannel(NioSocketChannel nc) throws Exception {
				ChannelPipeline pipeline = nc.pipeline();
				pipeline.addLast("decoder", new LengthFieldBasedFrameDecoder(
						lenFieldDto.getMaxFrameLength(), lenFieldDto.getLengthFieldOffset(), lenFieldDto.getLengthFieldLength(),
						lenFieldDto.getLengthAdjustment(), lenFieldDto.getInitialBytesToStrip()));
				pipeline.addLast("encoder", lenFieldEnClass.newInstance());
				pipeline.addLast("handler", handlerClass.newInstance());
			}
		});
	}

}
