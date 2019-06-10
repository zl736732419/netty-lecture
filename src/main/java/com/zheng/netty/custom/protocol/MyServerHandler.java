package com.zheng.netty.custom.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

/**
 * @Author zhenglian
 * @Date 2019/6/10
 */
public class MyServerHandler extends SimpleChannelInboundHandler<CustomProtocol> {
    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CustomProtocol msg) throws Exception {
        System.out.println("come from client request, count:" + (++count));
        System.out.println(msg);
        
        String uuid = UUID.randomUUID().toString();
        byte[] content = uuid.getBytes(CharsetUtil.UTF_8);
        int length = content.length;
        CustomProtocol protocol = new CustomProtocol(length, content);
        ctx.channel().writeAndFlush(protocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }
}
