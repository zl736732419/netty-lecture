package com.zheng.protobuf.rpc.impl;

import com.zheng.protobuf.rpc.MyRequest;
import com.zheng.protobuf.rpc.MyResponse;
import com.zheng.protobuf.rpc.StudentServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @Author zhenglian
 * @Date 2019/5/4
 */
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {
    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("server params: " + request.getUsername());
        responseObserver.onNext(MyResponse.newBuilder().setRealname("李四").build());
        responseObserver.onCompleted();
    }
}
