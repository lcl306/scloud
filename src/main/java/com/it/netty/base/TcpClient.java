package com.it.netty.base;

import com.it.netty.util.GlobalName;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TcpClient {
	
	public static Channel connect(final ChannelInitializer<SocketChannel> ci){
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Channel c = null;
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.AUTO_READ, true);
            //b.option(ChannelOption.TCP_NODELAY, true);
            b.handler(ci);
            ChannelFuture f = b.connect(GlobalName.IP, GlobalName.PORT).sync();
            c =f.channel();
            c.closeFuture().sync();
        }catch(Exception e){
        	e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
        }
        return c;
    }

}
