package com.zheng.protobuf.rpc.impl;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @Author zhenglian
 * @Date 2019/5/4
 */
public class StudentServer {
    private static final Logger logger = Logger.getLogger(StudentServer.class.getName());
    private final int port;
    private final Server server;
    
    public StudentServer(int port) {
        this(ServerBuilder.forPort(port), port);
    }
    
    public StudentServer(ServerBuilder<?> serverBuilder, int port) {
        this.port = port;
        this.server = serverBuilder.addService(new StudentServiceImpl())
                .build();
    }
    
    public void start() throws IOException {
        server.start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may has been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                StudentServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }
    
    public void stop() {
        if (null != server) {
            server.shutdown();
        }
    }
    
    private void blockUntilShutdown() throws InterruptedException {
        if (null != server) {
            server.awaitTermination();
        }
    }
    
    public static void main(String[] args) throws Exception {
        StudentServer server = new StudentServer(8899);
        server.start();
        server.blockUntilShutdown();
    }
}
