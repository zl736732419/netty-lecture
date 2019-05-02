package com.zheng.netty.example6;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author zhenglian
 * @Date 2019/5/2
 */
public class ProtobufServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {
        System.out.println("name: " + msg.getName());
        System.out.println("age: " + msg.getAge());
        System.out.println("address: " + msg.getAddress());
    }
}
