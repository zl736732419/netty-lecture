package com.zheng.netty.second;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @Author zhenglian
 * @Date 2019/4/28
 */
public class TestSocketClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("收到服务器信息：" + msg);
        ctx.writeAndFlush(UUID.randomUUID().toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 触发服务端客户端消息发送
        ctx.writeAndFlush("heart beat");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }
}
