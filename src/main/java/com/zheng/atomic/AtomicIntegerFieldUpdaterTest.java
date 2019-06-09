package com.zheng.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Author zhenglian
 * @Date 2019/6/9
 */
public class AtomicIntegerFieldUpdaterTest {
    
    public static void main(String[] args) {
        Person person = new Person();

        AtomicIntegerFieldUpdater<Person> updater = AtomicIntegerFieldUpdater.newUpdater(Person.class, "age");
        
        for (int i = 0; i < 10; i ++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    System.out.println(person.age++); // concurrent conflict
                    System.out.println(updater.getAndAdd(person, 1));
                }
            }).start();
        }
    }
    
    
    
    private static class Person {
        public volatile int age = 1;
    }
    
}
