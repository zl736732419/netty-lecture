package com.zheng.netty.example4;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

/**
 * @Author zhenglian
 * @Date 2019/5/1
 */
public class HeartBeatClient {
    public static void main(String[] args) throws Exception {
        EventLoopGroup boss = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(boss)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new IdleStateHandler(5, 3, 10));
                            pipeline.addLast(new StringDecoder())
                                    .addLast(new StringEncoder())
                                    .addLast(new HeartBeatClientHandler());
                        }
                    });
            ChannelFuture connect = bootstrap.connect(new InetSocketAddress("127.0.0.1", 8899)).sync();
            System.out.println("client start...");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                connect.channel().writeAndFlush(reader.readLine());
            }
        } finally {
            boss.shutdownGracefully();
        }
    }
}
