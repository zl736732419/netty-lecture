package com.zheng.netty.sources.eventloopgroup;

import io.netty.util.NettyRuntime;

/**
 * @Author zhenglian
 * @Date 2019/5/21
 */
public class EventLoopGroupThreadTest {
    public static void main(String[] args) {
        // 8
        System.out.println(NettyRuntime.availableProcessors() * 2);
    }
}
