package com.it.netty.obj;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import com.it.netty.base.TcpServer;
import com.it.netty.util.GlobalName;

public class TcpObjServer {
	
	public static <T extends ChannelHandler> Channel start(final Class<T> handlerClass){
		return TcpServer.start(new ChannelInitializer<NioSocketChannel>() {
			@Override
			protected void initChannel(NioSocketChannel nc) throws Exception {
				ChannelPipeline pipeline = nc.pipeline();
				//添加对象解码器 负责对序列化POJO对象进行解码 设置对象序列化最大长度为1M 防止内存溢出
				//设置线程安全的WeakReferenceMap对类加载器进行缓存 支持多线程并发访问  防止内存溢出 
				pipeline.addLast(new ObjectDecoder(GlobalName.MAX_SERVER_OBJECT_SIZE,
						ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
				//添加对象编码器 在服务器对外发送消息的时候自动将实现序列化的POJO对象编码
				pipeline.addLast(new ObjectEncoder());
				pipeline.addLast("handler", handlerClass.newInstance());
			}
		});
	}

}
