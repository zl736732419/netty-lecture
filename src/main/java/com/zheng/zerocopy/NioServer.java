package com.zheng.zerocopy;

import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author zhenglian
 * @Date 2019/5/20
 */
public class NioServer {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(true);
        serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
        serverSocketChannel.bind(new InetSocketAddress(8899));
        System.out.println("server listen on 8899...");
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int byteRead = 0;
            long start = System.currentTimeMillis();
            while (true) {
                int read = socketChannel.read(buffer);
                if (-1 == read) {
                    break;
                }
                byteRead += read;
                buffer.rewind();
            }
            socketChannel.close();
            // 56.7M文件大小，耗费1051ms
            System.out.println("读取字节数：" + byteRead + ", 耗时：" + (System.currentTimeMillis() - start) + "ms");
        }
    }
}
