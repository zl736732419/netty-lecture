package com.zheng.netty.custom.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @Author zhenglian
 * @Date 2019/6/10
 */
public class CustomDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("decode!");
        int length = in.readInt();
        byte[] content = new byte[length];
        in.readBytes(content);
        
        CustomProtocol protocol = new CustomProtocol(length, content);
        out.add(protocol);
    }
}
