package com.zheng.protobuf.rpc.impl;

import com.zheng.protobuf.rpc.MyRequest;
import com.zheng.protobuf.rpc.MyResponse;
import com.zheng.protobuf.rpc.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @Author zhenglian
 * @Date 2019/5/4
 */
public class StudentClient {

    private static final Logger logger = Logger.getLogger(StudentClient.class.getName());

    private final ManagedChannel channel;
    private final StudentServiceGrpc.StudentServiceBlockingStub blockingStub;
    private final StudentServiceGrpc.StudentServiceStub asyncStub;

    public StudentClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext());
    }

    public StudentClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        blockingStub = StudentServiceGrpc.newBlockingStub(channel);
        asyncStub = StudentServiceGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
    
    public static void main(String[] args) throws Exception {
        StudentClient client = new StudentClient("localhost", 8899);
        client.getRealNameByUsername("xiaozhang");
        client.shutdown();
    }

    private void getRealNameByUsername(String username) {
        MyRequest request = MyRequest.newBuilder().setUsername(username).build();
        MyResponse response = blockingStub.getRealNameByUsername(request);
        System.out.println("client response: " + response.getRealname());
    }
}
