package com.zyz.lambdaLearn;

import java.util.*;

/**
 * @author zyz
 * @date 2018/6/22
 */
public class ArrayTest {

    public static void main(String[] args) {
        String[] atp = {"java","php","c","c#","java","net"};
//        速度最快，无序，去重。
        Set<String> hashSet = new HashSet<>();
        hashSet.addAll(Arrays.asList(atp));
        hashSet.forEach(s -> System.out.println("hashSet：" + s));
//      根据插入顺序排序，去重。
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.addAll(Arrays.asList(atp));
        linkedHashSet.forEach(s -> System.out.println("linkedHashSet：" + s));
//      如果是数字，根据数字大小排序，如果是对象，则需要自定义排序规则，根据排序规则排序，去重。
        Set<String> treeSet = new TreeSet<>();
        treeSet.addAll(Arrays.asList(atp));
        treeSet.forEach(s -> System.out.println("treeSet：" + s));
    }

}
