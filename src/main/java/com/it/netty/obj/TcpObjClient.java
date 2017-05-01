package com.it.netty.obj;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import com.it.netty.base.TcpClient;
import com.it.netty.util.GlobalName;

public class TcpObjClient {
	
	public static <T extends ChannelHandler> Channel connect(final Class<T> handlerClass){
		return TcpClient.connect(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel sc){
				ChannelPipeline pipeline = sc.pipeline();
				//需要与服务器对应
				//添加POJO对象解码器 禁止缓存类加载器
				pipeline.addLast(new ObjectDecoder(GlobalName.MAX_CLIENT_OBJECT_SIZE,
						ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
				//设置发送消息编码器
				pipeline.addLast(new ObjectEncoder());
				//设置网络IO处理器
				try {
					pipeline.addLast("handler", handlerClass.newInstance());
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
