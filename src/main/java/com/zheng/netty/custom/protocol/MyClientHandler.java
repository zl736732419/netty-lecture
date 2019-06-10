package com.zheng.netty.custom.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

/**
 * @Author zhenglian
 * @Date 2019/6/10
 */
public class MyClientHandler extends SimpleChannelInboundHandler<CustomProtocol> {
    private int count;
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CustomProtocol msg) throws Exception {
        System.out.println("receive from server, count:" + (++count));
        System.out.println(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int length;
        byte[] content;
        String msg;
        CustomProtocol protocol;
        for (int i = 0; i < 10; i++) {
            msg = UUID.randomUUID().toString();
            content = msg.getBytes(CharsetUtil.UTF_8);
            length = content.length;
            protocol = new CustomProtocol(length, content);
            ctx.channel().writeAndFlush(protocol);
        }
    }
}
