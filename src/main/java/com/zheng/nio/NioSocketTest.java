package com.zheng.nio;

import com.google.common.collect.Lists;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Author zhenglian
 * @Date 2019/5/13
 */
public class NioSocketTest {
    public static void main(String[] args) throws Exception {
        List<Integer> ports = Lists.newArrayList(5000, 5001, 5002, 5003, 5004);

        Selector selector = Selector.open();
        
        ports.stream()
                .forEach(port -> {
                    try {
                        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                        serverSocketChannel.bind(new InetSocketAddress(port));
                        serverSocketChannel.configureBlocking(false);
                        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                        System.out.println("端口: " + port + "已注册");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        
        System.out.println("server listening on " + ports);
        while (true) {
            int select = selector.select();
            if (select <= 0) {
                continue;
            }
            
            System.out.println("keys: " + selector.keys());
            
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    accept(key, selector);
                } else if (key.isReadable()) {
                    read(key);
                }
                // 处理完事件后需要将其从对应的集合中删除
                iterator.remove();
            }
        }
    }

    private static void read(SelectionKey key) throws Exception {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(512);
        int byteRead = 0;
        int read;
        while (true) {
            buffer.clear();
            read = socketChannel.read(buffer);
            if (read <= 0) {
                break;
            }

            byteRead += read;

            buffer.flip();
            socketChannel.write(buffer);
        }
        System.out.println("读取: " + byteRead + "字节，客户端: " + socketChannel.getRemoteAddress());
        // 注意当客户端断开连接时，原来的keys中还是存在key,但是这个key已经失效了，需要cancel
        if (read == -1) {
            key.cancel();
            System.out.println("客户端" + socketChannel.getRemoteAddress() + "已关闭连接");
        }
    }

    private static void accept(SelectionKey key, Selector selector) throws Exception {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("客户端: " + socketChannel.getRemoteAddress() + "已建立连接");
    }
}
