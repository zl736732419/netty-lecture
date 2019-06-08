package com.zheng.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @Author zhenglian
 * @Date 2019/6/8
 */
public class StringByteBufTest {
    public static void main(String[] args) {
        ByteBuf buf = Unpooled.copiedBuffer("你hello world", CharsetUtil.UTF_8);
        if (buf.hasArray()) { // 判断是否是堆缓冲区
            byte[] bytes = buf.array();
            System.out.println(new String(bytes, 0, bytes.length, CharsetUtil.UTF_8));
            
            System.out.println(buf);
            
            System.out.println("offset: " + buf.arrayOffset());
            System.out.println("reader index: " + buf.readerIndex());
            System.out.println("writer index: " + buf.writerIndex());
            System.out.println("capacity: " + buf.capacity());
            
            for (int i = 0; i < buf.readableBytes(); i++) {
                System.out.println((char) buf.getByte(i));
            }

            System.out.println(buf.getCharSequence(0,4, CharsetUtil.UTF_8));
            System.out.println(buf.getCharSequence(4, 6, CharsetUtil.UTF_8));
        }
    }
}
