package com.zheng.nio;

import java.io.FileOutputStream;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author zhenglian
 * @Date 2019/5/9
 */
public class FileChannelTest {
    public static void main(String[] args) throws Exception {
//        read();
        write();
    }

    private static void write() throws Exception {
        FileOutputStream output = new FileOutputStream("src/main/resources/nio/nio_out.txt");
        FileChannel channel = output.getChannel();
        byte[] bytes = "hello world".getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        channel.write(buffer);
        
        channel.close();
    }

    private static void read() throws Exception {
        URI uri = FileChannelTest.class.getResource("/nio/nio.txt").toURI();
        Path path = Paths.get(uri);
        FileChannel channel = FileChannel.open(path);

        ByteBuffer buffer = ByteBuffer.allocate(128);
        channel.read(buffer);

        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get());
        }
    }
}
