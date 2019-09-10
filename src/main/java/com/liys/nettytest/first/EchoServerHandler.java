package com.liys.nettytest.first;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 继承ChannelInBoundHandler接口，用来响应入站事件响应的相关方法。
 * 此类继承ChannelInBoundHandlerAdapter类即可，它对ChannelInBoundHandler中的方法进行了实现
 * @author liys
 * @2019年9月10日下午9:32:33
 * @version 1.0
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter{
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf) msg;
		System.out.println("server received:" + in.toString(CharsetUtil.UTF_8));
		ctx.write(in);
	}
	
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
	

}
