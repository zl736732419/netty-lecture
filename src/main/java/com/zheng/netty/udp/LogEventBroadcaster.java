package com.zheng.netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;

/**
 * @Author zhenglian
 * @Date 2019/6/17
 */
public class LogEventBroadcaster {
    private final Bootstrap bootstrap;
    private final EventLoopGroup group;
    private final File file;
    
    public LogEventBroadcaster(InetSocketAddress address, File file) {
        this.file = file;
        bootstrap = new Bootstrap();
        group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)
                .handler(new LogEventEncoder(address));
    }
    
    public void run() throws Exception {
        Channel channel = bootstrap.bind(0).sync().channel();
        long pointer = 0;
        while (true) {
            long len = file.length();
            if (len < pointer) {
                pointer = len;
            } else if (len > pointer) {
                RandomAccessFile raf = new RandomAccessFile(file, "r");
                raf.seek(pointer); // 从上一次读取的位置开始
                String line;
                while ((line = raf.readLine()) != null) {
                    System.out.println("write line: " + line);
                    channel.writeAndFlush(new LogEvent(null, file.getAbsolutePath(), line, null));
                }
                pointer = raf.getFilePointer();
                raf.close(); // 完成一次读写
            }
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.interrupted();
                break;
            }
        }
    }
    
    public void stop() {
        group.shutdownGracefully();
    }
    
    public static void main(String[] args) {
        InetSocketAddress address = new InetSocketAddress("255.255.255.255", 8888);
//        String filePath = "C:\\Users\\zhenglian\\Desktop\\test.log";
        String filePath = "C:\\Users\\Administrator\\Desktop\\test.log";
        File file = new File(filePath);
        LogEventBroadcaster broadcaster = new LogEventBroadcaster(address, file);
        try {
            broadcaster.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            broadcaster.stop();
        }
    }
}
