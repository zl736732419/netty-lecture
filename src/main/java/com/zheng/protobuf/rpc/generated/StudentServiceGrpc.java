package com.zheng.protobuf.rpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * service rpc中的请求和响应要求参数必须是message类型
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.20.0)",
    comments = "Source: Student.proto")
public final class StudentServiceGrpc {

  private StudentServiceGrpc() {}

  public static final String SERVICE_NAME = "com.zheng.protobuf.StudentService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.zheng.protobuf.rpc.generated.MyRequest,
      com.zheng.protobuf.rpc.generated.MyResponse> getGetRealNameByUsernameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetRealNameByUsername",
      requestType = com.zheng.protobuf.rpc.generated.MyRequest.class,
      responseType = com.zheng.protobuf.rpc.generated.MyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zheng.protobuf.rpc.generated.MyRequest,
      com.zheng.protobuf.rpc.generated.MyResponse> getGetRealNameByUsernameMethod() {
    io.grpc.MethodDescriptor<com.zheng.protobuf.rpc.generated.MyRequest, com.zheng.protobuf.rpc.generated.MyResponse> getGetRealNameByUsernameMethod;
    if ((getGetRealNameByUsernameMethod = StudentServiceGrpc.getGetRealNameByUsernameMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetRealNameByUsernameMethod = StudentServiceGrpc.getGetRealNameByUsernameMethod) == null) {
          StudentServiceGrpc.getGetRealNameByUsernameMethod = getGetRealNameByUsernameMethod = 
              io.grpc.MethodDescriptor.<com.zheng.protobuf.rpc.generated.MyRequest, com.zheng.protobuf.rpc.generated.MyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.zheng.protobuf.StudentService", "GetRealNameByUsername"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zheng.protobuf.rpc.generated.MyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zheng.protobuf.rpc.generated.MyResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetRealNameByUsername"))
                  .build();
          }
        }
     }
     return getGetRealNameByUsernameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zheng.protobuf.rpc.generated.StudentRequest,
      com.zheng.protobuf.rpc.generated.StudentResponse> getGetStudentsByAgeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStudentsByAge",
      requestType = com.zheng.protobuf.rpc.generated.StudentRequest.class,
      responseType = com.zheng.protobuf.rpc.generated.StudentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.zheng.protobuf.rpc.generated.StudentRequest,
      com.zheng.protobuf.rpc.generated.StudentResponse> getGetStudentsByAgeMethod() {
    io.grpc.MethodDescriptor<com.zheng.protobuf.rpc.generated.StudentRequest, com.zheng.protobuf.rpc.generated.StudentResponse> getGetStudentsByAgeMethod;
    if ((getGetStudentsByAgeMethod = StudentServiceGrpc.getGetStudentsByAgeMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetStudentsByAgeMethod = StudentServiceGrpc.getGetStudentsByAgeMethod) == null) {
          StudentServiceGrpc.getGetStudentsByAgeMethod = getGetStudentsByAgeMethod = 
              io.grpc.MethodDescriptor.<com.zheng.protobuf.rpc.generated.StudentRequest, com.zheng.protobuf.rpc.generated.StudentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.zheng.protobuf.StudentService", "GetStudentsByAge"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zheng.protobuf.rpc.generated.StudentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zheng.protobuf.rpc.generated.StudentResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetStudentsByAge"))
                  .build();
          }
        }
     }
     return getGetStudentsByAgeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zheng.protobuf.rpc.generated.StudentRequest,
      com.zheng.protobuf.rpc.generated.StudentResponseList> getGetStudentWrapperByAgesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStudentWrapperByAges",
      requestType = com.zheng.protobuf.rpc.generated.StudentRequest.class,
      responseType = com.zheng.protobuf.rpc.generated.StudentResponseList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.zheng.protobuf.rpc.generated.StudentRequest,
      com.zheng.protobuf.rpc.generated.StudentResponseList> getGetStudentWrapperByAgesMethod() {
    io.grpc.MethodDescriptor<com.zheng.protobuf.rpc.generated.StudentRequest, com.zheng.protobuf.rpc.generated.StudentResponseList> getGetStudentWrapperByAgesMethod;
    if ((getGetStudentWrapperByAgesMethod = StudentServiceGrpc.getGetStudentWrapperByAgesMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetStudentWrapperByAgesMethod = StudentServiceGrpc.getGetStudentWrapperByAgesMethod) == null) {
          StudentServiceGrpc.getGetStudentWrapperByAgesMethod = getGetStudentWrapperByAgesMethod = 
              io.grpc.MethodDescriptor.<com.zheng.protobuf.rpc.generated.StudentRequest, com.zheng.protobuf.rpc.generated.StudentResponseList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.zheng.protobuf.StudentService", "GetStudentWrapperByAges"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zheng.protobuf.rpc.generated.StudentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zheng.protobuf.rpc.generated.StudentResponseList.getDefaultInstance()))
                  .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetStudentWrapperByAges"))
                  .build();
          }
        }
     }
     return getGetStudentWrapperByAgesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zheng.protobuf.rpc.generated.StudentRequest,
      com.zheng.protobuf.rpc.generated.StudentResponse> getGetStudentsByAgesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStudentsByAges",
      requestType = com.zheng.protobuf.rpc.generated.StudentRequest.class,
      responseType = com.zheng.protobuf.rpc.generated.StudentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.zheng.protobuf.rpc.generated.StudentRequest,
      com.zheng.protobuf.rpc.generated.StudentResponse> getGetStudentsByAgesMethod() {
    io.grpc.MethodDescriptor<com.zheng.protobuf.rpc.generated.StudentRequest, com.zheng.protobuf.rpc.generated.StudentResponse> getGetStudentsByAgesMethod;
    if ((getGetStudentsByAgesMethod = StudentServiceGrpc.getGetStudentsByAgesMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetStudentsByAgesMethod = StudentServiceGrpc.getGetStudentsByAgesMethod) == null) {
          StudentServiceGrpc.getGetStudentsByAgesMethod = getGetStudentsByAgesMethod = 
              io.grpc.MethodDescriptor.<com.zheng.protobuf.rpc.generated.StudentRequest, com.zheng.protobuf.rpc.generated.StudentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.zheng.protobuf.StudentService", "GetStudentsByAges"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zheng.protobuf.rpc.generated.StudentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zheng.protobuf.rpc.generated.StudentResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetStudentsByAges"))
                  .build();
          }
        }
     }
     return getGetStudentsByAgesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StudentServiceStub newStub(io.grpc.Channel channel) {
    return new StudentServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StudentServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new StudentServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StudentServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new StudentServiceFutureStub(channel);
  }

  /**
   * <pre>
   * service rpc中的请求和响应要求参数必须是message类型
   * </pre>
   */
  public static abstract class StudentServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getRealNameByUsername(com.zheng.protobuf.rpc.generated.MyRequest request,
        io.grpc.stub.StreamObserver<com.zheng.protobuf.rpc.generated.MyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetRealNameByUsernameMethod(), responseObserver);
    }

    /**
     */
    public void getStudentsByAge(com.zheng.protobuf.rpc.generated.StudentRequest request,
        io.grpc.stub.StreamObserver<com.zheng.protobuf.rpc.generated.StudentResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetStudentsByAgeMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.zheng.protobuf.rpc.generated.StudentRequest> getStudentWrapperByAges(
        io.grpc.stub.StreamObserver<com.zheng.protobuf.rpc.generated.StudentResponseList> responseObserver) {
      return asyncUnimplementedStreamingCall(getGetStudentWrapperByAgesMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.zheng.protobuf.rpc.generated.StudentRequest> getStudentsByAges(
        io.grpc.stub.StreamObserver<com.zheng.protobuf.rpc.generated.StudentResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getGetStudentsByAgesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetRealNameByUsernameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.zheng.protobuf.rpc.generated.MyRequest,
                com.zheng.protobuf.rpc.generated.MyResponse>(
                  this, METHODID_GET_REAL_NAME_BY_USERNAME)))
          .addMethod(
            getGetStudentsByAgeMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.zheng.protobuf.rpc.generated.StudentRequest,
                com.zheng.protobuf.rpc.generated.StudentResponse>(
                  this, METHODID_GET_STUDENTS_BY_AGE)))
          .addMethod(
            getGetStudentWrapperByAgesMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.zheng.protobuf.rpc.generated.StudentRequest,
                com.zheng.protobuf.rpc.generated.StudentResponseList>(
                  this, METHODID_GET_STUDENT_WRAPPER_BY_AGES)))
          .addMethod(
            getGetStudentsByAgesMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.zheng.protobuf.rpc.generated.StudentRequest,
                com.zheng.protobuf.rpc.generated.StudentResponse>(
                  this, METHODID_GET_STUDENTS_BY_AGES)))
          .build();
    }
  }

  /**
   * <pre>
   * service rpc中的请求和响应要求参数必须是message类型
   * </pre>
   */
  public static final class StudentServiceStub extends io.grpc.stub.AbstractStub<StudentServiceStub> {
    private StudentServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceStub(channel, callOptions);
    }

    /**
     */
    public void getRealNameByUsername(com.zheng.protobuf.rpc.generated.MyRequest request,
        io.grpc.stub.StreamObserver<com.zheng.protobuf.rpc.generated.MyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetRealNameByUsernameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getStudentsByAge(com.zheng.protobuf.rpc.generated.StudentRequest request,
        io.grpc.stub.StreamObserver<com.zheng.protobuf.rpc.generated.StudentResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetStudentsByAgeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.zheng.protobuf.rpc.generated.StudentRequest> getStudentWrapperByAges(
        io.grpc.stub.StreamObserver<com.zheng.protobuf.rpc.generated.StudentResponseList> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getGetStudentWrapperByAgesMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.zheng.protobuf.rpc.generated.StudentRequest> getStudentsByAges(
        io.grpc.stub.StreamObserver<com.zheng.protobuf.rpc.generated.StudentResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getGetStudentsByAgesMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * service rpc中的请求和响应要求参数必须是message类型
   * </pre>
   */
  public static final class StudentServiceBlockingStub extends io.grpc.stub.AbstractStub<StudentServiceBlockingStub> {
    private StudentServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.zheng.protobuf.rpc.generated.MyResponse getRealNameByUsername(com.zheng.protobuf.rpc.generated.MyRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetRealNameByUsernameMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.zheng.protobuf.rpc.generated.StudentResponse> getStudentsByAge(
        com.zheng.protobuf.rpc.generated.StudentRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getGetStudentsByAgeMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * service rpc中的请求和响应要求参数必须是message类型
   * </pre>
   */
  public static final class StudentServiceFutureStub extends io.grpc.stub.AbstractStub<StudentServiceFutureStub> {
    private StudentServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zheng.protobuf.rpc.generated.MyResponse> getRealNameByUsername(
        com.zheng.protobuf.rpc.generated.MyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetRealNameByUsernameMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_REAL_NAME_BY_USERNAME = 0;
  private static final int METHODID_GET_STUDENTS_BY_AGE = 1;
  private static final int METHODID_GET_STUDENT_WRAPPER_BY_AGES = 2;
  private static final int METHODID_GET_STUDENTS_BY_AGES = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final StudentServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(StudentServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_REAL_NAME_BY_USERNAME:
          serviceImpl.getRealNameByUsername((com.zheng.protobuf.rpc.generated.MyRequest) request,
              (io.grpc.stub.StreamObserver<com.zheng.protobuf.rpc.generated.MyResponse>) responseObserver);
          break;
        case METHODID_GET_STUDENTS_BY_AGE:
          serviceImpl.getStudentsByAge((com.zheng.protobuf.rpc.generated.StudentRequest) request,
              (io.grpc.stub.StreamObserver<com.zheng.protobuf.rpc.generated.StudentResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_STUDENT_WRAPPER_BY_AGES:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getStudentWrapperByAges(
              (io.grpc.stub.StreamObserver<com.zheng.protobuf.rpc.generated.StudentResponseList>) responseObserver);
        case METHODID_GET_STUDENTS_BY_AGES:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getStudentsByAges(
              (io.grpc.stub.StreamObserver<com.zheng.protobuf.rpc.generated.StudentResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class StudentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StudentServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.zheng.protobuf.rpc.generated.StudentProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StudentService");
    }
  }

  private static final class StudentServiceFileDescriptorSupplier
      extends StudentServiceBaseDescriptorSupplier {
    StudentServiceFileDescriptorSupplier() {}
  }

  private static final class StudentServiceMethodDescriptorSupplier
      extends StudentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    StudentServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (StudentServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StudentServiceFileDescriptorSupplier())
              .addMethod(getGetRealNameByUsernameMethod())
              .addMethod(getGetStudentsByAgeMethod())
              .addMethod(getGetStudentWrapperByAgesMethod())
              .addMethod(getGetStudentsByAgesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
