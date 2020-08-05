package com.zyz.learn;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zyz
 * @date 2018/7/18
 */
@TestAnnotation(msg = "hello")
public class AnnotationTest {

    @Check(value = "hi")
    int a;



    @Perform
    public void testMethod() {
    }


    @SuppressWarnings("deprecation")
    public void test1() {
        EmployeeInfo employeeInfo = new EmployeeInfo();
        employeeInfo.one();
        employeeInfo.two();
    }


    /**
     * @Retention –什么时候使用该注解
     * 取值RetentionPolicy有：
     * SOURCE：在源文件中有效（即源文件保留）
     * CLASS：在class文件中有效（即class保留）
     * RUNTIME：在运行时有效（即运行时保留）
     * @Target –注解用于什么地方
     * 取值ElementType有：
     * ANNOTATION_TYPE：可以给一个注解进行注解
     * CONSTRUCTOR：用于描述构造器
     * FIELD：可以给属性进行注解
     * LOCAL_VARIABLE：用于描述局部变量
     * METHOD：用于描述方法
     * PACKAGE：用于描述包
     * PARAMETER：可以给一个方法内的参数进行注解
     * TYPE：用于描述类、接口(包括注解类型) 或枚举声明
     * @Inherited – 是否允许子类继承该注解
     * @Documented –注解是否将包含在JavaDoc中
     * @Repeatable — 表示注解可重复使用在同一层面（1.8）
     *                              @a(name = "1")
     *                              @a(name = "2")
     *                              @a(name = "3")
     *                              String name;
     */
    private static Map getEmployeeInfo(Class<?> clazz) {
        HashMap<String, String> info = new HashMap<>();
        //获取类成员变量，clazz.getDeclaredFields() = EmployeeInfo.class.getDeclaredFields()
        Field[] fields = clazz.getDeclaredFields();
        //遍历
        for (Field field : fields) {
//            isAnnotationPresent方法判断类是否应用了该注解，Field.isA......，A.class.isA.....
            if (field.isAnnotationPresent(AnTest.class)) {
//                getAnnotation方法获取注解的值
                AnTest company = field.getAnnotation(AnTest.class);
                info.put("AnTest", company.id() + "：" + company.name() + "：" + company.address());
            }
        }
        return info;
    }

    public static void main(String[] args) {
        Map fruitInfo = getEmployeeInfo(EmployeeInfo.class);
        System.out.println(fruitInfo);
        System.out.println("--------------------------------------------");

        boolean hasAnnotation = AnnotationTest.class.isAnnotationPresent(TestAnnotation.class);

        if (hasAnnotation) {
            TestAnnotation testAnnotation = AnnotationTest.class.getAnnotation(TestAnnotation.class);
            //获取类的注解
            System.out.println("id:" + testAnnotation.id());
            System.out.println("msg:" + testAnnotation.msg());
        }

        try {
            Field a = AnnotationTest.class.getDeclaredField("a");
            a.setAccessible(true);
            //获取一个成员变量上的注解
            Check check = a.getAnnotation(Check.class);

            if (check != null) {
                System.out.println("check value:" + check.value());
            }

            Method testMethod = AnnotationTest.class.getDeclaredMethod("testMethod");

            if (testMethod != null) {
                // 获取方法中的注解
                Annotation[] ans = testMethod.getAnnotations();
                for (int i = 0; i < ans.length; i++) {
                    System.out.println("method testMethod annotation:" + ans[i].annotationType().getSimpleName());
                }
            }
        } catch (NoSuchFieldException | SecurityException | NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
