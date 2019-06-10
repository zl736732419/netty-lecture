package com.zheng.netty.custom.protocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Author zhenglian
 * @Date 2019/6/9
 */
public class MyClient {
    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(boss)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new CustomDecoder())
                                    .addLast(new CustomEncoder())
                                    .addLast(new MyClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect(new InetSocketAddress("localhost", 8899)).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();  
        } finally {
            boss.shutdownGracefully();
        }
        
    }
}
