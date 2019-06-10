package com.zheng.netty.custom.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author zhenglian
 * @Date 2019/6/10
 */
public class CustomEncoder extends MessageToByteEncoder<CustomProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, CustomProtocol msg, ByteBuf out) throws Exception {
        System.out.println("encode!");
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());
    }
}
