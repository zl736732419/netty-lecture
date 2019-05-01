package com.zheng.netty.example1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Author zhenglian
 * @Date 2019/4/28
 */
public class TestHttpServer {
    public static void main(String[] args) throws Exception {
        // 线程池
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker);
            // 设置channel工厂
            bootstrap.channel(NioServerSocketChannel.class);
            // 设置管道工厂
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
    
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast("httpServerCodec", new HttpServerCodec());
                    pipeline.addLast("testHttpServerHandler", new TestHttpServerHandler());
                }
            });
            // 绑定端口
            ChannelFuture channelFuture = bootstrap.bind(8899).sync();
            System.out.println("server listening on 8899");
            channelFuture.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
