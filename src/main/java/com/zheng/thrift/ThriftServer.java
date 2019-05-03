package com.zheng.thrift;

import com.zheng.thrift.generated.PersonService;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * @Author zhenglian
 * @Date 2019/5/2
 */
public class ThriftServer {
    public static void main(String[] args) throws Exception {
//        server1();
        server2();
    }

    private static void server2() throws Exception {
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
        THsHaServer.Args args = new THsHaServer.Args(socket)
                .minWorkerThreads(2)
                .maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());
        
        args.transportFactory(new TFramedTransport.Factory());
        args.protocolFactory(new TCompactProtocol.Factory());
        args.processorFactory(new TProcessorFactory(processor));
        
        TServer server = new THsHaServer(args);
        System.out.println("server listen on 8899.");
        server.serve();
    }

    private static void server1() throws Exception {
        TServerTransport transport = new TServerSocket(8899);

        PersonServiceImpl handler = new PersonServiceImpl();
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(handler);
        TServer.Args args = new TServer.Args(transport)
                .processor(processor);
        
        TServer server = new TSimpleServer(args);
        System.out.println("server listen on 8899.");
        server.serve();
    }
}
