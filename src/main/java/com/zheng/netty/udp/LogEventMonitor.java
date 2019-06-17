package com.zheng.netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;

/**
 * @Author zhenglian
 * @Date 2019/6/18
 */
public class LogEventMonitor {
    private EventLoopGroup group;
    private Bootstrap bootstrap;
    
    public LogEventMonitor() {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new LogEventDecoder())
                                .addLast(new LogEventHandler());
                    }
                });
    }
    
    private Channel bind(InetSocketAddress address) {
        return bootstrap.bind(address).syncUninterruptibly().channel();
    }
    
    private void stop() {
        group.shutdownGracefully();
    }
    
    public static void main(String[] args) {
        LogEventMonitor monitor = new LogEventMonitor();
        try {
            Channel channel = monitor.bind(new InetSocketAddress(8888));
            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            monitor.stop();
        }
    }
}
