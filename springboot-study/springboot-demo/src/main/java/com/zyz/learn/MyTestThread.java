package com.zyz.learn;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zyz
 * @date 2018/6/19
 */
public class MyTestThread implements Runnable {

    private final CountDownLatch startSignal;

    public MyTestThread(CountDownLatch startSignal) {
        super();
        this.startSignal = startSignal;
    }

    @Override
    public void run() {
        try {
            startSignal.await(); //一直阻塞当前线程，直到计时器的值为0
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("do work!" + System.currentTimeMillis());
    }


    public static void main(String[] args) {
        // 初始化计数器为 一
        CountDownLatch start = new CountDownLatch(1);
        ExecutorService service = Executors.newCachedThreadPool();
        //模擬5个线程
        for (int i = 0; i < 5; i++) {
            MyTestThread tt = new MyTestThread(start);
            service.execute(tt);
        }
        start.countDown();//计数器減一  所有线程释放 同时跑
    }


}
