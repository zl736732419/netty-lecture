package com.zheng.netty.custom.protocol;


import io.netty.util.CharsetUtil;

/**
 * @Author zhenglian
 * @Date 2019/6/10
 */
public class CustomProtocol {
    private int length;
    private byte[] content;

    public CustomProtocol(int length, byte[] content) {
        this.length = length;
        this.content = content;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("length: ").append(length)
                .append("content:").append(new String(content, 0, content.length, CharsetUtil.UTF_8))
                .toString();
    }
}
