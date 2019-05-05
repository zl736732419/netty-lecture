package com.zheng.protobuf.rpc;

import com.zheng.protobuf.rpc.generated.MyRequest;
import com.zheng.protobuf.rpc.generated.MyResponse;
import com.zheng.protobuf.rpc.generated.StudentRequest;
import com.zheng.protobuf.rpc.generated.StudentResponse;
import com.zheng.protobuf.rpc.generated.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
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
        client.getStudentsByAge(20);
        client.shutdown();
    }

    private void getStudentsByAge(int age) throws Exception {
        StudentRequest request = StudentRequest.newBuilder().setAge(20).build();
        final CountDownLatch finishLatch = new CountDownLatch(1);
        asyncStub.getStudentsByAge(request, new StreamObserver<StudentResponse>() {
            @Override
            public void onNext(StudentResponse value) {
                System.out.println("===============================");
                System.out.println("name: " + value.getName());
                System.out.println("age: " + value.getAge());
                System.out.println("city: " + value.getCity());
            }

            @Override
            public void onError(Throwable t) {
                warning("StudentService Failed: {0}", Status.fromThrowable(t));
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                info("Finished StudentService");
                finishLatch.countDown();
            }
        });
        finishLatch.await();
    }

    private void info(String msg, Object... params) {
        logger.log(Level.INFO, msg, params);
    }

    private void warning(String msg, Object... params) {
        logger.log(Level.WARNING, msg, params);
    }

    private void getRealNameByUsername(String username) {
        MyRequest request = MyRequest.newBuilder().setUsername(username).build();
        MyResponse response = blockingStub.getRealNameByUsername(request);
        System.out.println("client response: " + response.getRealname());
    }
}
