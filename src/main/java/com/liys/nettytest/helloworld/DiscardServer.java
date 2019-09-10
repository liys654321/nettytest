package com.liys.nettytest.helloworld;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 服务器
 * @author liys
 * @2019年9月9日下午9:45:28
 * @version 1.0
 */
public class DiscardServer {
	
	public static void main(String[] args) {
		// 1.创建两个线程组，一个用来接受客户端的接入，另一个用来处理客户端的请求
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			// 2.创建一个服务引导类，起到整个服务的引导作用
			ServerBootstrap server = new ServerBootstrap();
			// 3.给引导类设置线程组，设置channel，设置childChannel以及参数
			server.group(bossGroup, workGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new DiscardServerHandler());
				}
			})
			.option(ChannelOption.SO_BACKLOG, 128)
			.childOption(ChannelOption.SO_KEEPALIVE, true);
			// 4.给服务绑定端口
			ChannelFuture sync = server.bind(9999).sync();
			sync.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
		
		
		
		
		
	}
	
	
}
