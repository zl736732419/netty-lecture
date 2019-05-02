package com.zheng.netty.example61;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author zhenglian
 * @Date 2019/5/2
 */
public class ProtobufMessageServerHandler extends SimpleChannelInboundHandler<MessageInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageInfo.MyMessage msg) throws Exception {
        MessageInfo.MyMessage.DataType dataType = msg.getDataType();
        System.out.println(dataType);
        switch(dataType) {
            case PERSON_TYPE:
                MessageInfo.Person person = msg.getPerson();
                System.out.println("name: " + person.getName());
                System.out.println("age: " + person.getAge());
                System.out.println("address: " + person.getAddress());
                break;
            case DOG_TYPE:
                MessageInfo.Dog dog = msg.getDog();
                System.out.println("name: " + dog.getName());
                System.out.println("age: " + dog.getAge());
                break;
            case CAT_TYPE:
                MessageInfo.Cat cat = msg.getCat();
                System.out.println("name: " + cat.getName());
                System.out.println("age: " + cat.getAge());
                break;
        }
    }
}
