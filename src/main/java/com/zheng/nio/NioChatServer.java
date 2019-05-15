package com.zheng.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author zhenglian
 * @Date 2019/5/14
 */
public class NioChatServer {
    
    private static HashSet<SocketChannel> clients = new HashSet<>();
    
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8899));
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("server listen on 8899...");
        
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    handleAccept(key, selector);
                } else if(key.isReadable()) {
                    handleRead(key);
                }
                // 处理完事件后删除该事件
                iterator.remove();
            }
        }
    }

    private static void handleRead(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(512);
        int read;
        try {
            while (true) {
                buffer.clear();
                read = client.read(buffer);
                if (read <= 0) {
                    break;
                }
                buffer.flip();
                String message = new String(buffer.array(), 0, buffer.limit(), StandardCharsets.UTF_8);
                System.out.println("客户端【"+client.getRemoteAddress()+"】: " + message);
                publishMsg(client, message);
            }
            if (read == -1) { // 客户端关闭
                key.cancel();
                clients.remove(client);
                System.out.println("客户端【"+client.getRemoteAddress()+"】离开了");
            }
        }catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            key.cancel();
        }
    }

    private static void publishMsg(SocketChannel client, String message) throws Exception {
        for (SocketChannel c : clients) {
            StringBuilder builder = new StringBuilder();
            builder.append("客户端【");
            if (c == client) {
                builder.append("自己");
            } else {
                builder.append(client.getRemoteAddress());
            }
            builder.append("】: ");
            builder.append(message);
            ByteBuffer buffer = ByteBuffer.wrap(builder.toString().getBytes());
            buffer.put(builder.toString().getBytes(StandardCharsets.UTF_8));
            buffer.flip();
            c.write(buffer);
        }
    }

    private static void handleAccept(SelectionKey key, Selector selector) throws Exception {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel client = serverSocketChannel.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
        clients.add(client);
        System.out.println("客户端【"+client.getRemoteAddress()+"】上线了");
    }
}
