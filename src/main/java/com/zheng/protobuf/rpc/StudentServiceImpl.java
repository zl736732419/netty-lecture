package com.zheng.protobuf.rpc;

import com.zheng.protobuf.rpc.generated.MyRequest;
import com.zheng.protobuf.rpc.generated.MyResponse;
import com.zheng.protobuf.rpc.generated.StudentRequest;
import com.zheng.protobuf.rpc.generated.StudentResponse;
import com.zheng.protobuf.rpc.generated.StudentResponseList;
import com.zheng.protobuf.rpc.generated.StudentServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * @Author zhenglian
 * @Date 2019/5/4
 */
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

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

    @Override
    public StreamObserver<StudentRequest> getStudentWrapperByAges(StreamObserver<StudentResponseList> responseObserver) {
        return new StreamObserver<StudentRequest>() {

            final long startTime = System.nanoTime();
            StudentResponseList.Builder builder = StudentResponseList.newBuilder();
            AtomicInteger count = new AtomicInteger(0);
            @Override
            public void onNext(StudentRequest value) {
                System.out.println("server params: " + value.getAge());
                builder.addStudents(StudentResponse.newBuilder().setName("张三" + count.incrementAndGet()).setAge(value.getAge()).setCity("深圳").build());
            }

            @Override
            public void onError(Throwable t) {
                logger.log(Level.WARNING, "StudentService cancelled");
            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(builder.build());
                responseObserver.onCompleted();
                long seconds = NANOSECONDS.toSeconds(System.nanoTime() - startTime);
                System.out.println("cost " + seconds + "s");
            }
        };
    }
}
