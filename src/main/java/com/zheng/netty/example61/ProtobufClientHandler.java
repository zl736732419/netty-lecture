package com.zheng.netty.example61;

import com.zheng.netty.example6.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @Author zhenglian
 * @Date 2019/5/2
 */
public class ProtobufClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int seed;
        MessageInfo.MyMessage.Builder builder = MessageInfo.MyMessage.newBuilder();
        for (int i = 0; i < 10; i++) {
            seed = new Random().nextInt(3);
            switch(seed) {
                case 0:
                    builder.setDataType(MessageInfo.MyMessage.DataType.PERSON_TYPE);
                    builder.setPerson(MessageInfo.Person.newBuilder()
                            .setName("小张").setAge(26).setAddress("深圳").build());
                    break;
                case 1:
                    builder.setDataType(MessageInfo.MyMessage.DataType.DOG_TYPE);
                    builder.setDog(MessageInfo.Dog.newBuilder()
                            .setName("旺财").setAge(2).build());
                    break;
                case 2:
                    builder.setDataType(MessageInfo.MyMessage.DataType.CAT_TYPE);
                    builder.setCat(MessageInfo.Cat.newBuilder()
                            .setName("小花").setAge(1).build());
                    break;
            }
            ctx.channel().writeAndFlush(builder.build());
        }
    }
}
