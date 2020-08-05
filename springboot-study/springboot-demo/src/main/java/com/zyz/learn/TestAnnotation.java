package com.zyz.learn;

import java.lang.annotation.*;

/**
 * @author zyz
 * @date 2019/4/24
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {

    int id() default 1;

    String msg() default "msg";

}
