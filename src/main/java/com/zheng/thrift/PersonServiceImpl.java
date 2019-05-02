package com.zheng.thrift;

import com.zheng.thrift.generated.DataException;
import com.zheng.thrift.generated.Person;
import com.zheng.thrift.generated.PersonService;
import org.apache.thrift.TException;

/**
 * @Author zhenglian
 * @Date 2019/5/2
 */
public class PersonServiceImpl implements PersonService.Iface {
    @Override
    public Person getPersonByUsername(String username) throws DataException, TException {
        System.out.println("get person by username params: " + username);
        Person person = new Person();
        person.setUsername("张三");
        person.setAge(26);
        person.setMarried(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("save person params: ");
        System.out.println("username: " + person.getUsername());
        System.out.println("age: " + person.getAge());
        System.out.println("married: " + person.isMarried());
    }
}
