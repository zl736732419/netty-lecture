package com.zheng.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @Author zhenglian
 * @Date 2019/6/8
 */
public class CompositeBufferTest {
    public static void main(String[] args) {
        CompositeByteBuf buf = Unpooled.compositeBuffer();
        ByteBuf cmpBuf1 = Unpooled.buffer(10);
        ByteBuf cmpBuf2 = Unpooled.buffer(10);
        ByteBuf cmpBuf3 = Unpooled.directBuffer(8);
        buf.addComponents(cmpBuf1, cmpBuf2, cmpBuf3);
        
        buf.forEach(System.out::println);
        
        for (int i = 0; i < 25; i++) {
            buf.writeByte(i);
        }

        int length = buf.readableBytes();
        for (int i = 0; i < length; i++) {
            System.out.println((int) buf.readByte());
        }
    }
}
