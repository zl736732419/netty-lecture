package com.zheng.protobuf.rpc;

import com.zheng.protobuf.rpc.generated.MyRequest;
import com.zheng.protobuf.rpc.generated.MyResponse;
import com.zheng.protobuf.rpc.generated.StudentRequest;
import com.zheng.protobuf.rpc.generated.StudentResponse;
import com.zheng.protobuf.rpc.generated.StudentServiceGrpc;
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

    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("server params: " + request.getAge());
        for (int i = 0; i < 3; i++) {
            responseObserver.onNext(StudentResponse.newBuilder().setName("小张" + i).setAge(20+i).setCity("深圳").build());
        }
        responseObserver.onCompleted();
    }
}
