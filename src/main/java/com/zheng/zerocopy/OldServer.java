package com.zheng.zerocopy;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传统IO Stream服务器
 * @Author zhenglian
 * @Date 2019/5/20
 */
public class OldServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.setReuseAddress(true);
        serverSocket.bind(new InetSocketAddress(8899));
        System.out.println("server listen on 8899...");
        while (true) {
            int byteRead = 0;
            Socket socket = serverSocket.accept();
            InputStream input = socket.getInputStream();
            byte[] bytes = new byte[1024];
            long start = System.currentTimeMillis();
            while (true) {
                int read = input.read(bytes, 0, bytes.length);
                if (-1 == read) {
                    break;
                }
                byteRead += read;
            }
            socket.close();
            // 56.7M文件大小，耗费4828ms
            System.out.println("读取字节数：" + byteRead + ", 耗时：" + (System.currentTimeMillis() - start) + "ms");
        }
    }
}
