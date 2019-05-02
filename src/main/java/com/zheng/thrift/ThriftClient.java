package com.zheng.thrift;

import com.zheng.thrift.generated.Person;
import com.zheng.thrift.generated.PersonService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @Author zhenglian
 * @Date 2019/5/2
 */
public class ThriftClient {
    public static void main(String[] args) throws Exception {
        TTransport transport = new TSocket("localhost", 8899);
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);
        // 执行rpc
        Person person = client.getPersonByUsername("张三");
        System.out.println("username: " + person.getUsername());
        System.out.println("age: " + person.getAge());
        System.out.println("married: " + person.isMarried());
        
        System.out.println("----------------");
        Person person1 = new Person();
        person1.setUsername("李四");
        person1.setAge(25);
        person1.setMarried(false);
        client.savePerson(person1);
        // 执行完成调用后关闭连接
        transport.close();
    }
}
