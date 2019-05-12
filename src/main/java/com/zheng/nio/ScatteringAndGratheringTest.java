package com.zheng.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @Author zhenglian
 * @Date 2019/5/12
 */
public class ScatteringAndGratheringTest {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(8899));
        int length = 2 + 3 + 4;

        ByteBuffer[] buffers = new ByteBuffer[3];
        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);
        
        System.out.println("server listen on 8899...");
        
        SocketChannel channel = serverChannel.accept();
        while (true) {
            long byteRead = 0;
            while (byteRead < length) {
                long read = channel.read(buffers);
                if (read <= 0) {
                    break;
                }
                byteRead += read;
                Arrays.stream(buffers)
                        .map(buffer -> "position: " + buffer.position() 
                            + ", limit: " + buffer.limit() + ", capacity: " + buffer.capacity())
                        .forEach(str -> System.out.println(str));
            }
            Arrays.stream(buffers)
                    .forEach(buffer -> buffer.flip());
            long byteWrite = channel.write(buffers);

            Arrays.stream(buffers)
                    .forEach(buffer -> buffer.clear());
            System.out.println("read bytes: " + byteRead + ", write bytes: " + byteWrite);
        }
    }
}
