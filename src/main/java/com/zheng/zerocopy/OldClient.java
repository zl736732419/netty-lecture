package com.zheng.zerocopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Author zhenglian
 * @Date 2019/5/20
 */
public class OldClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8899);
        OutputStream output = socket.getOutputStream();
        
        String path = "E:\\resources\\apache-jmeter-5.1.zip";
        FileInputStream input = new FileInputStream(new File(path));
        int size = input.available();
        int byteWrite = 0;
        byte[] bytes = new byte[1024];
        while (true) {
            int read = input.read(bytes, 0, bytes.length);
            if (-1 == read) {
                break;
            }
            byteWrite += read;
            output.write(bytes, 0, read);
        }
        System.out.println("write : " + byteWrite + ", file size: " + size);
        System.out.println("done!");
        output.close();
        input.close();
    }
}
