package com.zyz.lambdaLearn;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

/**
 * @author zyz
 * @date 2018/6/21
 */
public class BaseStream {

    static List<Person> javaProgrammers = new ArrayList<Person>() {
        {
            add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
            add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
            add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
            add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
            add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
            add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
            add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
            add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
            add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
            add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1200));
            add(new Person("Addison", "Pam1", "Java programmer1", "female1", 341, 1300));
        }
    };

    static List<Person> phpProgrammers = new ArrayList<Person>() {
        {
            add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
            add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
            add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
            add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
            add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
            add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
            add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
            add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
            add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
            add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
        }
    };


    /**
     * Stream是对集合的包装,通常和lambda一起使用。
     * 使用lambdas可以支持许多操作,如 map, filter, limit, sorted, count, min, max, sum, collect 等等。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("给程序员加薪 5% :");
        Consumer<Person> personConsumer = person -> person.setSalary(person.getSalary() / 100 * 5 + person.getSalary());
        javaProgrammers.forEach(personConsumer);
        phpProgrammers.forEach(personConsumer);
//        System.out.println("所有程序员的姓名！");
        /*javaProgrammers.forEach((java) -> System.out.println("java：" + java.getFirstName() + java.getSalary()));
        phpProgrammers.forEach((php) -> System.out.println("php：" + php.getFirstName() + php.getSalary()));*/

//        自定义filter，使用filter方法
        Predicate<Person> ageFilter = (p) -> (p.getAge() > 25);
        Predicate<Person> salaryFilter = (p) -> (p.getSalary() > 1400);
        Predicate<Person> sexFilter = (p) -> ("female".equals(p.getGender()));
        System.out.println("下面是年龄大于 24岁且月薪在$1,400以上的女PHP程序员:");
        phpProgrammers.stream().filter(ageFilter).filter((p) -> (p.getSalary() > 1400)).filter(sexFilter).forEach((p) -> System.out.println("php：" + p.getAge() + p.getGender() + p.getSalary()));
//          使用limit方法，截取前三个女性
        javaProgrammers.stream().filter(sexFilter).limit(3).forEach((java) -> System.out.println("java：" + java.getFirstName() + java.getSalary()));
//      使用排序方法，根据姓名排序，并且取前五个
        List<Person> sortedJavaProgrammers = javaProgrammers.stream().sorted(
                (java, java2) -> (java.getFirstName().compareTo(java2.getFirstName()))
        ).limit(5).collect(toList());
        sortedJavaProgrammers.forEach((p) -> System.out.println("java姓名排序：" + p.getFirstName() + p.getGender()));
//        使用min方法，查询工资最低员工
        Person person = javaProgrammers.stream().min((p1, p2) -> (p1.getSalary() - p2.getSalary())).get();
        System.out.println("java工资最低：" + person.getSalary());
//        使用max方法，查询工资最高员工
        Person person1 = phpProgrammers.stream().max((p1, p2) -> (p1.getSalary() - p2.getSalary())).get();
        System.out.println("php工资最高：" + person1.getSalary());
//      使用map 方法,我们可以使用 collect 方法来将我们的结果集放到一个字符串,一个 Set 或一个TreeSet中:
        String java = javaProgrammers.stream().map(Person::getFirstName).collect(joining(";"));
        System.out.println("将员工姓放在String中：" + java);
        Set<String> people = javaProgrammers.stream().map(Person::getFirstName).collect(toSet());
        people.forEach((p) -> System.out.println("放在set中：" + p));
        TreeSet<String> people1 = phpProgrammers.stream().map(Person::getFirstName).collect(toCollection(TreeSet::new));
        people1.forEach((p) -> System.out.println("放在TreeSet中：" + p));
//        distinct()方法
        javaProgrammers.parallelStream().distinct().forEach((a) -> System.out.println("去重复：" + a.getFirstName()));
//        计算统计发给java员工的总金额，并行流
        int totalSalary = javaProgrammers
                .parallelStream()
                .mapToInt(p -> p.getSalary())
                .sum();
        System.out.println("总金额：" + totalSalary);
        //     计算 count, min, max, sum, and average for numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        IntSummaryStatistics stats = numbers
                .stream()
                .mapToInt((x) -> x)
                .summaryStatistics();

        System.out.println("List中最大的数字 : " + stats.getMax());
        System.out.println("List中最小的数字 : " + stats.getMin());
        System.out.println("所有数字的总和   : " + stats.getSum());
        System.out.println("所有数字的平均值 : " + stats.getAverage());

        System.out.println("根据name排序");
//        第一种根据collections方法配合lambda表达式排序，最优方式为第二种方式
//        Collections.sort(javaProgrammers, (java1, java2) -> (java1.getFirstName().compareTo(java2.getFirstName())));
        javaProgrammers.sort(Comparator.comparing(Person::getFirstName));
        javaProgrammers.forEach((p) -> System.out.println(p.getFirstName()));
//        测试并行流和顺序流效率
        testPerformance();
//      自定义写方法根据姓去除重复
        List<Person> personList = new ArrayList<>();
        javaProgrammers.forEach((p2) -> {
            boolean b = personList.stream().anyMatch(u -> u.getFirstName().equals(p2.getFirstName()));
            if (!b) {
                personList.add(p2);
            }
        });
        personList.forEach(a -> System.out.println("根据姓去除重复：" + a.getFirstName()));
    }

    public static void testPerformance() {
        long t0 = System.nanoTime();
        //初始化一个范围100万整数流,求能被2整除的数字，toArray（）是终点方法
        IntStream.range(0, 1_000_000).filter(p -> p % 2 == 0).toArray();
        long t1 = System.nanoTime();
        //和上面功能一样，这里是用并行流来计算
        IntStream.range(0, 1_000_000).parallel().filter(p -> p % 2 == 0).toArray();
        long t2 = System.nanoTime();
        //比较并行流和顺序流效率
        System.out.printf("serial: %.2fs, parallel %.2fs%n", (t1 - t0) * 1e-9, (t2 - t1) * 1e-9);
    }

}
