package com.zheng.netty.sources.eventloopgroup;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhenglian
 * @Date 2019/5/22
 */
public class ScheduleTaskTest {
    public static ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
    public static void main(String[] args) {
//        fixRate();
        fixDelay();
    }

    private static void fixDelay() {
        service.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
//                int a = 1/0; # 任务执行过程中发生异常会终止定时任务
                System.out.println("start");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1, 3, TimeUnit.SECONDS);
    }

    private static void fixRate() {
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("start");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1, 3, TimeUnit.SECONDS);
    }
}
