package com.zyz.learn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zyz
 * @date 2018/6/19
 */
public class CountDownLatchTest1 implements Runnable {

    final AtomicInteger number = new AtomicInteger();
    volatile boolean bol = false;

    @Override
    public void run() {
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
            System.out.println("并发数量为" + number.intValue());
        }
    }

    public static void main(String[] args) {
//        推荐手动创建线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        CountDownLatchTest1 test = new CountDownLatchTest1();
        for (int i=0;i<10;i++) {
            pool.execute(test);
        }
    }

}