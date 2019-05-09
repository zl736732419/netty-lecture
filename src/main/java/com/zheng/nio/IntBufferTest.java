package com.zheng.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @Author zhenglian
 * @Date 2019/5/9
 */
public class IntBufferTest {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);
        int seed;
        for (int i = 0; i < 10; i++) {
            seed = new SecureRandom().nextInt(20);
            buffer.put(seed);
        }
        
        buffer.flip();
        
        while (buffer.hasRemaining()) {
            seed = buffer.get();
            System.out.println(seed);
        }
    }
}
