package com.zyz.lambdaLearn;

/**
 * @author zyz
 * @date 2018/6/21
 */
@FunctionalInterface
public interface LambdaTest {

    /**
     * 用lambda表达式可以直接给int赋值
     * jdk1.8
     * @param str
     * @return
     */
    int a(String str);

    /**
     * 在接口中方法加上default关键字，可以实现方法体。
     * 需要实现此接口才能用此方法，但可以不用重写此方法。
     * jdk1.8
     */
    default void love() {
        System.out.println("why interface can do it?");
    }

    /**
     * 同上理。
     * 此方法可以直接调用。
     * jdk1.8
     * @return
     */
    static int read() {
        return 0;
    }

    default void lower() {
        System.out.println("函数啊");
    }

    static int write() {
        return 1;
    }

}
