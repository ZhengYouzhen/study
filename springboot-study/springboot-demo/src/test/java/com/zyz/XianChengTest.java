package com.zyz;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.zyz.lambdaLearn.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author zyz
 * @date 2020/6/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class XianChengTest {

    private static Logger logger = LoggerFactory.getLogger(XianChengTest.class);

    static List<String> list = new ArrayList<String>() {
        {
            add("one1");
            add("one2");
            add("one3");
            add("one4");
            add("one5");
            add("one6");
            add("one7");
            add("one8");
            add("one9");
            add("one10");
            add("one11");
            add("one12");
        }
    };

    private int maxSize = 5;

    @Test
    public void testOne() {
        BigDecimal a = BigDecimal.ZERO;
        BigDecimal b = BigDecimal.ZERO;

        for (int i = 0; i < 5; i++) {
            a = a.add(new BigDecimal("2").multiply(new BigDecimal("2")));
        }
        for (int j = 0; j < 5; j++) {
            b.add(new BigDecimal("2").multiply(new BigDecimal("2")));
        }
        System.out.println(a);
        System.out.println(b);


        System.out.println("testOne----start");
        int listSize = list.size();
        int runsize = (listSize / maxSize) + 1;
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("express-pool-%d").build();
//                    设置三个线程处理
        ExecutorService singleThreadPool = new ThreadPoolExecutor(3, 5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        try {
            // 多线程处理
            List<String> applyList = null;
            for (int i = 0; i < runsize; i++) {
                if ((i + 1) == runsize) {
                    int startIndex = (i * maxSize);
                    int endIndex = list.size();
                    applyList = list.subList(startIndex, endIndex);
                } else {
                    int startIndex = i * maxSize;
                    int endIndex = (i + 1) * maxSize;
                    applyList = list.subList(startIndex, endIndex);
                }
                MultiRun hpRunnable = new MultiRun(String.valueOf(runsize), applyList);
                singleThreadPool.execute(hpRunnable);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            singleThreadPool.shutdown();
        }
        System.out.println("testOne----end");
    }


    @Test
    public void testTwo() {
        System.out.println("testTwo----start");
        int listSize = list.size();
        int runsize = (listSize / maxSize) + 1;
        ThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(runsize);
        CountDownLatch countDownLatch = new CountDownLatch(runsize);
        try {
            // 多线程处理
            List<String> applyList = null;
            for (int i = 0; i < runsize; i++) {
                if ((i + 1) == runsize) {
                    int startIndex = (i * maxSize);
                    int endIndex = list.size();
                    applyList = list.subList(startIndex, endIndex);
                } else {
                    int startIndex = i * maxSize;
                    int endIndex = (i + 1) * maxSize;
                    applyList = list.subList(startIndex, endIndex);
                }

                MultiRun hpRunnable = new MultiRun(String.valueOf(runsize), applyList);
                executor.execute(hpRunnable);
            }
            countDownLatch.await(5, TimeUnit.SECONDS);
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            executor.shutdown();
        }
        System.out.println("testTwo----end");
    }

    static class MultiRun implements Runnable {
        private List<String> stringList;
        private String userId;

        public MultiRun(String userId, List<String> stringList) {
            this.userId = userId;
            this.stringList = stringList;
        }

        @Override
        public void run() {
            logger.info("线程：{}", System.currentTimeMillis());
            try {
                for (String str : stringList) {
                    logger.info("userId：{}", str);
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }


}
