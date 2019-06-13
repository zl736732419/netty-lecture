package com.zheng.nettyinaction.nio;

import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 基于nio源生接口编程服务器
 * @Author zhenglian
 * @Date 2019/6/13
 */
public class NioHiServer {
    public void start(final int port) {
        new Thread(() -> {
            try {
                Selector selector = Selector.open();
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.bind(new InetSocketAddress(port));
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                ByteBuffer message = ByteBuffer.wrap("Hi\r\n".getBytes(CharsetUtil.UTF_8));
                while (true) {
                    selector.select();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        try {
                            if (key.isAcceptable()) {
                                ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                                SocketChannel socketChannel = channel.accept();
                                socketChannel.configureBlocking(false);
                                socketChannel.register(selector, SelectionKey.OP_READ
                                        | SelectionKey.OP_WRITE, message.duplicate());
                            } else if (key.isWritable()) {
                                SocketChannel channel = (SocketChannel) key.channel();
                                ByteBuffer msg = (ByteBuffer) key.attachment();
                                while (msg.hasRemaining()) {
                                    if (channel.write(msg) == 0) {
                                        break;
                                    }
                                }
                                // 关闭连接
                                channel.close();
                            }
                        } catch (Exception e) {
                            key.cancel();
                            key.channel().close();
                        }
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    public static void main(String[] args) {
        int port = 8899;
        new NioHiServer().start(port);
        System.out.println("server listen on " + port);
    }
}
