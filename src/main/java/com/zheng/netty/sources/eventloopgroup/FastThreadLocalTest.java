package com.zheng.netty.sources.eventloopgroup;

import io.netty.util.concurrent.FastThreadLocal;

/**
 * @Author zhenglian
 * @Date 2019/5/26
 */
public class FastThreadLocalTest {
    
    public static void main(String[] args) {
        FastThreadLocal<String> local = new FastThreadLocal<>();
        local.set("hello world");
        System.out.println(local.get());
        local.remove();
    }
}
