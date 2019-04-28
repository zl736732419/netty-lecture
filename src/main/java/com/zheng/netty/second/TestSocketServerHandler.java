package com.zheng.netty.second;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author zhenglian
 * @Date 2019/4/28
 */
public class TestSocketServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("收到客户端["+ctx.channel().remoteAddress()+"]消息：" + msg);
        // 返回服务器地址
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ctx.writeAndFlush(sf.format(now));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }
}
