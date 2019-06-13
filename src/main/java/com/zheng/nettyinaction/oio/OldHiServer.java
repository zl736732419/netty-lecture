package com.zheng.nettyinaction.oio;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传统io编程
 * @Author zhenglian
 * @Date 2019/6/13
 */
public class OldHiServer {
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            new Thread(() -> {
                while (true) {
                    try {
                        Socket socket = serverSocket.accept();
                        new Thread(new ClientHandler(socket)).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private class ClientHandler implements Runnable {
        private Socket socket;
        
        public ClientHandler(Socket socket) {
            this.socket = socket;
        } 
        
        @Override
        public void run() {
            OutputStream output = null;
            try {
                output = socket.getOutputStream();
                output.write("Hi!\r\n".getBytes(CharsetUtil.UTF_8));
                output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    output.close();
                    // 发送消息后直接关闭连接
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        int port = 8899;
        new OldHiServer().start(port);
        System.out.println("server listening on " + port);
    }
}
