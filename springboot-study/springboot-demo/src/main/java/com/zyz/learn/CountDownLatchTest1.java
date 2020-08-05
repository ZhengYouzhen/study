package com.zyz.learn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zyz
 * @date 2018/6/19
 */
public class CountDownLatchTest1 {

    final AtomicInteger number = new AtomicInteger();
    volatile boolean bol = false;

    Runnable runnable = () -> {
        System.out.println(number.getAndIncrement());
        synchronized (this) {
            try {
                if (!bol) {
                    System.out.println(bol);
                    bol = true;
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Holle world！" + System.currentTimeMillis());
        }
    };

    public static void main(String[] args) {
//        推荐手动创建线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        CountDownLatchTest1 test = new CountDownLatchTest1();
        for (int i = 0; i < 5; i++) {
            pool.execute(test.runnable);
        }
        pool.shutdown(); //任务结束，停止线程池的所有线程
    }

}
