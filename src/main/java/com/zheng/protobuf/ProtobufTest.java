package com.zheng.protobuf;

import java.util.Arrays;

/**
 * @Author zhenglian
 * @Date 2019/5/2
 */
public class ProtobufTest {
    public static void main(String[] args) throws Exception {
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setName("张三").setAge(20).setAddress("深圳").build();
        byte[] bytes = student.toByteArray();
        System.out.println("bytes:" + Arrays.toString(bytes));
        DataInfo.Student student1 = DataInfo.Student.parseFrom(bytes);
        System.out.println("name: " + student1.getName());
        System.out.println("age: " + student1.getAge());
        System.out.println("address: " + student1.getAddress());
    }
}
