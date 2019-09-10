package com.liys.nettytest.helloworld;

import java.nio.ByteBuffer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 丢弃协议就是对接受到的数据不做任何的处理直接丢弃。
 * @author liys
 * @2019年9月9日下午9:32:41
 * @version 1.0
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter{
	
	/**
	 * 读取数据
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		/*ByteBuf in = (ByteBuf)msg;
		
		while(in.isReadable()) {
			char readByte = (char) in.readByte();
			if(readByte == 0) {
				System.out.println();
			}else {
				System.out.print(readByte);
			}
			System.out.flush();
		}*/
		
		ctx.writeAndFlush(msg);
		
	}
	
	
	/**
	 * 如果发生异常则退出
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
