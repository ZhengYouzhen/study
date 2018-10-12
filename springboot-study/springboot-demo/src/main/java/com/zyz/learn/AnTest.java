package com.zyz.learn;

import java.lang.annotation.*;

/**
 * @author zyz
 * @date 2018/7/18
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AnTest {

    int id() default -1;

    String name() default "";

    String address() default "";

    String value () default "";

}
