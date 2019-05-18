package com.zheng.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @Author zhenglian
 * @Date 2019/5/18
 */
public class CharsetTest {
    public static void main(String[] args) throws Exception {
        FileInputStream input = new FileInputStream("src/main/resources/nio/nio_charset_input.txt");
        FileOutputStream output = new FileOutputStream("src/main/resources/nio/nio_charset_output.txt");
        
        FileChannel inputChannel = input.getChannel();
        FileChannel outputChannel = output.getChannel();

        MappedByteBuffer buffer = inputChannel.map(FileChannel.MapMode.READ_ONLY, 0, input.available());

        System.out.println(Charset.availableCharsets());
        
        Charset charset = Charset.forName("utf-8");
//        Charset charset = Charset.forName("iso-8859-1");
        CharsetEncoder encoder = charset.newEncoder();
        CharsetDecoder decoder = charset.newDecoder();

        CharBuffer charBuffer = decoder.decode(buffer);
        ByteBuffer byteBuffer = encoder.encode(charBuffer);
        outputChannel.write(byteBuffer);
    }
}
