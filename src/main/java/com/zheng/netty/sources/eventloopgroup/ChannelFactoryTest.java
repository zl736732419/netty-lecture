package com.zheng.netty.sources.eventloopgroup;

import java.lang.reflect.Constructor;

/**
 * @Author zhenglian
 * @Date 2019/5/26
 */
public class ChannelFactoryTest {
    private static class Person {
        public Person() {}
    }
    public static void main(String[] args) throws Exception {
        // 必须显示设置无参构造函数才能获取到
        Constructor<Person> constructor = Person.class.getConstructor();
        System.out.println(constructor);
    }
}
