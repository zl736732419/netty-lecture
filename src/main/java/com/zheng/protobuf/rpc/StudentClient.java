package com.zheng.protobuf.rpc;

import com.zheng.protobuf.rpc.generated.MyRequest;
import com.zheng.protobuf.rpc.generated.MyResponse;
import com.zheng.protobuf.rpc.generated.StudentRequest;
import com.zheng.protobuf.rpc.generated.StudentResponse;
import com.zheng.protobuf.rpc.generated.StudentResponseList;
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
        client.getStudentWrapperByAges();
        client.shutdown();
    }

    private void getStudentWrapperByAges() throws Exception {
        System.out.println("getStudentWrapperByAges");
        final CountDownLatch finishLatch = new CountDownLatch(1);
        StreamObserver<StudentRequest> requestObserver = asyncStub.getStudentWrapperByAges(new StreamObserver<StudentResponseList>() {
            @Override
            public void onNext(StudentResponseList value) {
                for (StudentResponse response : value.getStudentsList()) {
                    System.out.println("===============================");
                    System.out.println("name: " + response.getName());
                    System.out.println("age: " + response.getAge());
                    System.out.println("city: " + response.getCity());
                }
            }
            
            @Override
            public void onError(Throwable t) {
                finishLatch.countDown();
                logger.log(Level.WARNING, "StudentService getStudentWrapperByAges error");
            }

            @Override
            public void onCompleted() {
                finishLatch.countDown();
                logger.info("StudentService getStudentWrapperByAges success");
            }
        });
        try {
            for (int i = 0 ; i < 3; i++) {
                requestObserver.onNext(StudentRequest.newBuilder().setAge(20 + i).build());
                if (finishLatch.getCount() == 0) {
                    // RPC completed or errored before we finished sending.
                    // Sending further requests won't error, but they will just be thrown away.
                    return;
                }
            }
        } catch (Exception e) {
            // Cancel RPC
            requestObserver.onError(e);
        }
        requestObserver.onCompleted();
        // Receiving happens asynchronously
        if (!finishLatch.await(1, TimeUnit.MINUTES)) {
            warning("StudentService getStudentWrapperByAges can not finish within 1 minutes");
        }
    }

    private void getStudentsByAge(int age) throws Exception {
        System.out.println("getStudentsByAge");
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
                warning("StudentService getStudentsByAge Failed: {0}", Status.fromThrowable(t));
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                info("Finished StudentService getStudentsByAge");
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
        System.out.println("getRealNameByUsername");
        MyRequest request = MyRequest.newBuilder().setUsername(username).build();
        MyResponse response = blockingStub.getRealNameByUsername(request);
        System.out.println("client response: " + response.getRealname());
    }
}
