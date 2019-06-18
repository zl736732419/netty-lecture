package com.zheng.netty.udp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * @Author zhenglian
 * @Date 2019/6/18
 */
public class LogEventDecoder extends MessageToMessageDecoder<DatagramPacket> {
    
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, DatagramPacket packet, List<Object> list) throws Exception {
        ByteBuf data = packet.content();
        int idx = data.indexOf(0, data.readableBytes(), LogEvent.SEPERATOR);
        String filepath = data.slice(0, idx).toString(CharsetUtil.UTF_8);
        data.readerIndex(idx+1);
        String msg = data.slice(idx+1, data.readableBytes()).toString(CharsetUtil.UTF_8);
        data.writerIndex(data.writerIndex());
        LogEvent logEvent = new LogEvent(packet.sender(), filepath, msg, System.currentTimeMillis());
        list.add(logEvent);
    }
}
