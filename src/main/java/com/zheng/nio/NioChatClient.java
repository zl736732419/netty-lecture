package com.zheng.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author zhenglian
 * @Date 2019/5/15
 */
public class NioChatClient {
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    public static void main(String[] args) throws Exception {
        SocketChannel  socketChannel = SocketChannel.open();
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        // 必须在注册connect事件之后进行连接，否则无法收到connect事件
        socketChannel.connect(new InetSocketAddress("localhost", 8899));
        
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isConnectable()) {
                    handleConnect(key, selector);
                } else if (key.isReadable()) {
                    handleRead(key);
                }
                iterator.remove();
            }
        }
    }

    private static void handleRead(SelectionKey key) throws Exception {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(512);
        while (true) {
            buffer.clear();
            int read = socketChannel.read(buffer);
            if (read <= 0) {
                if (read == -1) {
                    key.cancel();
                }
                break;
            }
            buffer.flip();
            String msg = new String(buffer.array(), 0, buffer.limit(), StandardCharsets.UTF_8);
            System.out.println("response: " + msg);
        }
    }

    private static void handleConnect(SelectionKey key, Selector selector) throws Exception {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        if (socketChannel.isConnectionPending()) {
            socketChannel.finishConnect();
            System.out.println("服务器连接成功");
            socketChannel.register(selector, SelectionKey.OP_READ);
            executorService.submit(new ChatTask(socketChannel, key));
        }
    }
    
    private static class ChatTask implements Runnable {
        private SocketChannel socketChannel;
        private SelectionKey key;
        
        public ChatTask(SocketChannel socketChannel, SelectionKey key) {
            this.socketChannel = socketChannel;
            this.key = key;
        }
        
        @Override
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                while (true) {
                    String line = reader.readLine();
                    if (Objects.equals(line, "bye")) {
                        key.cancel();
                        break;
                    }
                    ByteBuffer buffer = ByteBuffer.wrap(line.getBytes(StandardCharsets.UTF_8));
                    socketChannel.write(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
