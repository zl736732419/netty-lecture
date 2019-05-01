package com.zheng.netty.example4;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @Author zhenglian
 * @Date 2019/5/1
 */
public class HeartBeatClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("服务器响应消息：" + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            IdleState state = event.state();
            switch (state) {
                case WRITER_IDLE:
                    ctx.channel().writeAndFlush("heart beat!");
                    break;
                case READER_IDLE:
                case ALL_IDLE:
                    ctx.channel().close();
                    break;
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
