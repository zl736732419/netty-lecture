package com.zheng.thrift;

import com.zheng.thrift.generated.PersonService;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * @Author zhenglian
 * @Date 2019/5/2
 */
public class ThriftServer {
    public static void main(String[] args) throws Exception {
        TServerTransport transport = new TServerSocket(8899);

        PersonServiceImpl handler = new PersonServiceImpl();
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(handler);
        
        TServer server = new TSimpleServer(new TServer.Args(transport).processor(processor));
        System.out.println("server listen on 8899.");
        server.serve();
    }
}
