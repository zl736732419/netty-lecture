package com.zheng.nio;

import java.nio.ByteBuffer;

/**
 * @Author zhenglian
 * @Date 2019/5/12
 */
public class ByteBufferTest {
    
    public static void main(String[] args) {
//        testType();
        testSlice();
    }

    private static void testSlice() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0 ; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }
        
        buffer.position(2);
        buffer.limit(6);
        
        // 分片buffer与原来buffer共享数据，只是坐标位置不同
        // offset就是作用于slice
        ByteBuffer sliceBuffer = buffer.slice();
        for (int i = 0; i < sliceBuffer.capacity(); i++) {
            buffer.put((byte) (sliceBuffer.get() * 2));
        }
        
        // 访问原来的buffer，会发现原来的buffer对应位置数据发生了改变
        buffer.position(0);
        buffer.limit(buffer.capacity());
        
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }

    private static void testType() {
        ByteBuffer buffer = ByteBuffer.allocate(64);
        buffer.putInt(15)
                .putLong(500000L)
                .putDouble(14.1234)
                .putChar('你')
                .putShort((short) 2)
                .putChar('我');

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
        System.out.println(buffer.getChar());
    }
}
