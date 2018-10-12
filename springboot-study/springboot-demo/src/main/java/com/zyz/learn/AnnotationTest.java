package com.zyz.learn;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zyz
 * @date 2018/7/18
 */
public class AnnotationTest {

    /**
    @Retention –什么时候使用该注解
        取值RetentionPolicy有：
            SOURCE:在源文件中有效（即源文件保留）
            CLASS:在class文件中有效（即class保留）
            RUNTIME:在运行时有效（即运行时保留）
    @Target –注解用于什么地方
        取值ElementType有：
            CONSTRUCTOR:用于描述构造器
            FIELD:用于描述域
            LOCAL_VARIABLE:用于描述局部变量
            METHOD:用于描述方法
            PACKAGE:用于描述包
            PARAMETER:用于描述参数
            TYPE:用于描述类、接口(包括注解类型) 或enum声明
    @Inherited – 是否允许子类继承该注解
     @Documented –注解是否将包含在JavaDoc中
     */

    public static Map getEmployeeInfo(Class<?> clazz){
        HashMap<String ,String> info = new HashMap<>();
        //获取类成员变量
        Field[] fields = clazz.getDeclaredFields();
        //遍历
        for (Field field: fields) {
            if (field.isAnnotationPresent(AnTest.class)) {
                AnTest company = field.getAnnotation(AnTest.class);
                info.put("AnTest",company.id()+":"+company.name()+":"+company.address());
            }
        }
        return info;
    }

    public static void main(String[] args) {
        Map fruitInfo = getEmployeeInfo(EmployeeInfo.class);
        System.out.println(fruitInfo);
    }
}
