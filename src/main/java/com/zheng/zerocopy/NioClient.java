package com.zheng.zerocopy;

import java.io.File;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author zhenglian
 * @Date 2019/5/20
 */
public class NioClient {
    public static void main(String[] args) throws Exception {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(true);
        channel.connect(new InetSocketAddress("localhost", 8899));
        if (channel.isConnectionPending()) {
            channel.finishConnect();
        }
        String path = "E:\\resources\\apache-jmeter-5.1.zip";
        FileInputStream input = new FileInputStream(new File(path));
        FileChannel inputChannel = input.getChannel();
        long size = inputChannel.size();
        long byteWrite = 0;
        while (true) {
            long length = inputChannel.transferTo(byteWrite, size, channel);
            if (0 == length) {
                break;
            }
            byteWrite += length;
        }
        System.out.println("write : " + byteWrite + ", channel size: " + size);
        System.out.println("done!");
        channel.close();
        inputChannel.close();
    }
}
