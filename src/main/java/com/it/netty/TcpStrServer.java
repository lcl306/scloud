package com.it.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.ThreadFactory;

import org.apache.log4j.BasicConfigurator;

public class TcpStrServer {
	
	static final String WORK_POOL_NAME = "work-thread-pool";
	
	static final String SERVER_IP = "0.0.0.0";
	
	static final int SERVER_PORT = 83;
	
	static final int OPTION_PORT = 128;

	static{
		BasicConfigurator.configure();
	}
	
	public static void start(){
		//线程池
		EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
		ThreadFactory threadFactory = new DefaultThreadFactory(WORK_POOL_NAME);
		//SelectorProvider说明了这个EventLoop所使用的多路复用IO模型为操作系统决定
		EventLoopGroup workLoopGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors()*2, threadFactory, SelectorProvider.provider());
		try {
			//服务启动器
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			//指定netty的work线程和boss线程
			serverBootstrap.group(bossLoopGroup, workLoopGroup);
			//serverBootstrap.group(workLoopGroup); 说明boss线程和work线程共享一个线程池，一般这样已经够用
					
			//设置channel类型
			serverBootstrap.channel(NioServerSocketChannel.class);
			//通过水管，即pipeline设置拦截器即handler
			serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>(){

				@Override
				protected void initChannel(NioSocketChannel ch) throws Exception {
					//ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
					//ch.pipeline().addLast(new ByteArrayEncoder());
					//ch.pipeline().addLast(new ByteArrayDecoder());
					ch.pipeline().addLast(new TcpStrServerHandler());
					//ch.pipeline().addLast(new ByteArrayEncoder());
					//ch.pipeline().addLast(new ByteArrayDecoder());
				}
			});
					
			//设置netty服务器绑定的ip端口
			//serverBootstrap.option(ChannelOption.SO_BACKLOG, OPTION_PORT);
			//serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
			//启动server
			ChannelFuture f = serverBootstrap.bind(SERVER_PORT).sync();
			//wait至server socket close
		    f.channel().closeFuture().sync();
			//serverBootstrap.bind(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			//serverBootstrap.bind(new InetSocketAddress(SERVER_IP, 86); //还可以监听多个端口
		} catch (InterruptedException e){
			e.printStackTrace();
		}finally{
			//server 关闭后，关闭所有线程池
			workLoopGroup.shutdownGracefully();
			bossLoopGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) {
		TcpStrServer.start();
	}

}
