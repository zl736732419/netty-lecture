package com.zheng.netty.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author zhenglian
 * @Date 2019/6/18
 */
public class LogEventHandler extends SimpleChannelInboundHandler<LogEvent> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogEvent msg) throws Exception {
         StringBuilder builder = new StringBuilder();
         builder.append(msg.getReceivedTime())
                 .append("[")
                 .append(msg.getSource().toString())
                 .append("][")
                 .append(msg.getLogfile())
                 .append("]: ")
                 .append(msg.getMsg());
         System.out.println(builder.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
