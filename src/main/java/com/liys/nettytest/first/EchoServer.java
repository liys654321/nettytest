package com.liys.nettytest.first;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 服务器引导类
 * 
 * 		主要干两件事情：
 * 			1.绑定监听和接收连接请求的端口。
 * 			2.设置channel
 * 
 * @author liys
 * @2019年9月10日下午9:47:24
 * @version 1.0
 */
public class EchoServer {
	
	
	public static void main(String[] args) {
		// 创建两个线程池boss和worker，boss用来接收客户端的接入，worker用来处理客户端请求的数据
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		try {
			// 创建一个引导类
			ServerBootstrap server = new ServerBootstrap();
			// 配置参数以及channel
			server.group(boss, worker)
			.channel(NioServerSocketChannel.class)
			.localAddress(new InetSocketAddress(9999))
			.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new EchoServerHandler());
				}
			});
			ChannelFuture sync = server.bind().sync();
			sync.channel().close().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				boss.shutdownGracefully().sync();
				worker.shutdownGracefully().sync();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	

}
