package com.zheng.netty.example4;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author zhenglian
 * @Date 2019/5/1
 */
public class HeartBeatServerHandler extends SimpleChannelInboundHandler<String> {
    private int idleCount = 3;
    private AtomicInteger currentCount = new AtomicInteger(0);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        currentCount.set(0);
        System.out.println("收到客户端消息：" + msg);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ctx.channel().writeAndFlush(sf.format(new Date()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接["+ctx.channel().remoteAddress()+"]加入");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接["+ctx.channel().remoteAddress()+"]断开");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            IdleState state = event.state();
            String eventType = null;
            switch(state) {
                case READER_IDLE:
                    eventType = "读空闲";
                    break;
                case ALL_IDLE:
                    eventType = "读写空闲";
                    break;
            }
            System.out.println(eventType);
            // 发生心跳检测异常，关闭连接
            if (currentCount.incrementAndGet() > idleCount) {
                ctx.channel().close();
            } else {
                System.out.println("客户端连接["+ctx.channel().remoteAddress()+"]空闲：" + currentCount.get() + "次");
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }
}
