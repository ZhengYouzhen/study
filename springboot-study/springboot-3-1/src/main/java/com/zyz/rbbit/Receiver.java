package com.zyz.rbbit;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @author 娃娃鱼
 * @date 2018/3/28 11:08
 * 创建一个消息接受者
 */
@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
