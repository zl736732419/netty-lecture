package com.zheng.netty.second;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @Author zhenglian
 * @Date 2019/4/28
 */
public class TestSocketClient {
    public static void main(String[] args) throws Exception {
        EventLoopGroup boss = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(boss);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new LengthFieldBasedFrameDecoder(2048,0,4,0, 4))
                            .addLast(new StringDecoder())
                            .addLast(new LengthFieldPrepender(4))
                            .addLast(new StringEncoder())
                            .addLast(new TestSocketClientHandler());
                }
            });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
        }
    }
}
