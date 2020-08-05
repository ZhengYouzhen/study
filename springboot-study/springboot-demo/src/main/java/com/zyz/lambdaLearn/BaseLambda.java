package com.zyz.lambdaLearn;

import java.util.*;

import static java.lang.System.*;

/**
 * @author zyz
 * @date 2018/6/21
 */
public class BaseLambda {

    public static void main(String[] args) {
        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer", "Roger Federer",
                "Andy Murray", "Tomas Berdych",
                "John Isner"};
//        根据字符串的首字母排序---------------------------------------------
        /*Arrays.sort(atp, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1.compareTo(o2));
            }
        });*/
//        lambda表达式
//        Arrays.sort(atp, (String s1, String s2) -> (s1.compareTo(s2)));   下一行是简写
        Arrays.sort(atp, String::compareTo);
//        根据字符串空格后的字面排序----------------------------------------
        /*Arrays.sort(atp, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.substring(s1.indexOf(" ")).compareTo(s2.substring(s2.indexOf(" "))));
            }
        });*/
//        lambda表达式简化写法
        /*Comparator<String> comparator = (String s1, String s2) -> (s1.substring(s1.indexOf(" ")).compareTo(s2.substring(s2.indexOf(" "))));
        Arrays.sort(atp, comparator);*/
//        lambda表达式最简写法
//        Arrays.sort(atp, (String s1, String s2) -> (s1.substring(s1.indexOf(" ")).compareTo(s2.substring(s2.indexOf(" ")))));
//        Arrays.sort(atp, Comparator.comparing((String s) -> s.substring(s.indexOf(" "))));
//      根据字符串长度排序---------------------------------------------------
        /*Arrays.sort(atp, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.length() - s2.length());
            }
        });*/
//      lambda表达式
        /*Comparator<String> comparator = (String s1, String s2) -> (s1.length() - s2.length());
        Arrays.sort(atp, comparator);*/
//      lambda表达式
        /*Arrays.sort(atp, (String s1, String s2) -> (s2.length() - s1.length()));*/
//        根据最后一个字符排序-----------------------------------------------
        /*Comparator<String> comparator = (String s1, String s2) ->(s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 1));
        Arrays.sort(atp, comparator);*/

        List<String> players = Arrays.asList(atp);
//        lambada表达式遍历，-> ()，直接写返回值。-> {}，可以写方法。-> 传递参数，:: 调用方法
        players.forEach((player) ->
                out.println(player + ";")
        );
        /**
         players.forEach((player) ->{
         if (player != null) {
         System.out.println(player + "test;");
         } else {
         System.out.println("is null");
         }
         });
         */
//        players.forEach(System.out :: println);
//        匿名内部类
        new Thread(() -> out.println("hello world")).start();
//        接口的匿名内部类
        Runnable runnable = () -> out.println("is lambda!");
        runnable.run();
        out.println(a() + "-----------");
        out.println(LambdaTest.read() + "++++++++++++++++");
        LambdaTest lambdaTest = (String str) -> 5;
        lambdaTest.love();
        Set set = new LinkedHashSet();
    }

    public static int a() {
        String str = "str";
        LambdaTest lambdaTest = (x) -> 5;
        return lambdaTest.a(str);
    }

}
