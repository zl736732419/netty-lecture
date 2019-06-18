package com.zheng.netty.embedded;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.embedded.EmbeddedChannel;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  TODO
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年06月18日			zhenglian			    Initial.
 *
 * </pre>
 */
public class OutHandlerExceptionTest {
    public static void main(String[] args) {
        EmbeddedChannel channel = new EmbeddedChannel(new MsgEncoder());
        ChannelFuture future = channel.write("hello");
        future.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
    }
}
