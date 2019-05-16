package com.zheng.nio;

import java.io.FileInputStream;
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
//        write();
//        readWrite();
//        transferTo();
        transferFrom();
    }

    private static void transferFrom() throws Exception {
        FileInputStream input = new FileInputStream("src/main/resources/nio/nio.txt");
        FileOutputStream output = new FileOutputStream("src/main/resources/nio/nio_tran1.txt");

        FileChannel inputChannel = input.getChannel();
        FileChannel outputChannel = output.getChannel();
        outputChannel.transferFrom(inputChannel, 0, 5);
    }

    private static void transferTo() throws Exception {
        FileInputStream input = new FileInputStream("src/main/resources/nio/nio.txt");
        FileOutputStream output = new FileOutputStream("src/main/resources/nio/nio_tran.txt");

        FileChannel outputChannel = output.getChannel();
        FileChannel inputChannel = input.getChannel();
        inputChannel.transferTo(0, 5, outputChannel);
    }

    private static void readWrite() throws Exception {
        FileInputStream input = new FileInputStream("src/main/resources/nio/nio.txt");
        FileOutputStream output = new FileOutputStream("src/main/resources/nio/nio_out.txt");

        FileChannel inputChannel = input.getChannel();
        FileChannel outputChannel = output.getChannel();
        
        ByteBuffer buffer = ByteBuffer.allocate(512);
        
        int length;
        while(true) {
            // 清空buffer准备进行一次数据读取操作，
            // 没有这行会导致死循环，当positon = limit时，调用inputChannel.read()时返回0而不是-1
            /*
                参见源码 IOUtil.class
                readIntoNativeBuffer() {
                    ...
                    int var7 = var5 <= var6 ? var6 - var5 : 0;
                    if (var7 == 0) {
                        return 0;
                    }
                    ...
                }
                
             */
            
            buffer.clear(); 
            
            length = inputChannel.read(buffer);
            if (length == -1) {
                break;
            }
            
            buffer.flip();
            outputChannel.write(buffer);
        }
        System.out.println("file read finished!");
        inputChannel.close();
        outputChannel.close();
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
