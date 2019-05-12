package com.zheng.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URI;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author zhenglian
 * @Date 2019/5/12
 */
public class MappedByteBufferTest {
    
    public static void main(String[] args) throws Exception {
        // 内存映射文件操作，位于堆外内存
        URI uri = MappedByteBufferTest.class.getResource("/nio/mapped.txt").toURI();
        Path path = Paths.get(uri);
        File file = path.toFile();
        System.out.println("exist: " + file.exists() + "\n" + "path: " + file.getAbsolutePath());
        
        RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
        FileChannel channel = accessFile.getChannel();
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        
        buffer.put(0, (byte) 'a');
        buffer.put(3, (byte) 'b');
        
        accessFile.close();
    }
}
