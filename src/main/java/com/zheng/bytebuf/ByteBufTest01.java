package com.zheng.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @Author zhenglian
 * @Date 2019/6/8
 */
public class ByteBufTest01 {
    public static void main(String[] args) {
        ByteBuf buf = Unpooled.buffer(10);
        for (int i = 0; i < 10; i++) {
            buf.writeByte(i);
        }
        
        for (int i = 0; i < 10; i++) {
            System.out.println(buf.readByte());
        }
    }
}
