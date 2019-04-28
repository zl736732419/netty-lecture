package com.zheng.netty.third;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Author zhenglian
 * @Date 2019/4/29
 */
public class TestChatServerHandler extends SimpleChannelInboundHandler<String> {
    
    private ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        group.write("服务器["+channel.remoteAddress()+"] 加入了\n");
        group.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // 这里不用手动移除group中的channel, netty会自动处理
        Channel channel = ctx.channel();
        group.write("服务器["+channel.remoteAddress()+"] 离开了\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("服务器["+channel.remoteAddress()+"] 上线了\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("服务器["+channel.remoteAddress()+"] 离线了\n");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        group.forEach(ch -> {
            if (ch != channel) {
                ch.writeAndFlush("服务器["+channel.remoteAddress()+"]：" + msg + "\n");
            } else {
                ch.writeAndFlush("服务器[自己]：" + msg + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }
}
