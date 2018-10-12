package com.zyz;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyz.bean.User;
import com.zyz.dao.UserMapper;
import com.zyz.lambdaLearn.Person;
import com.zyz.learn.EnumTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

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

    @Test
    public void testdemo() throws IOException {
        String json = "{\"username\":\"tom\",\"company\":{\"companyName\":\"中华\",\"address\":\"北京\"},\"cars\":[\"奔驰\",\"宝马\"]}";
        String arrayJson = "[{\"number\":64,\"result\":\"SUCCESS\"},{\"number\":65,\"result\":\"FAILURE\"},{\"number\":66,\"result\":\"ABORTED\"},{\"number\":67,\"result\":\"SUCCESS\"}]";
//      接收单条数据-----------------------------------------------------------
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(json);
//      遍历，拿key
        Iterator<String> keys = jsonNode.fieldNames();
        while (keys.hasNext()) {
            String key = keys.next();
            System.out.println("key键是:" + key);
        }
//        根据key获取值
        JsonNode path = jsonNode.path("username");
        JsonNode resultValue = jsonNode.findValue("username");
        JsonNode resultPath = jsonNode.findPath("username");
        System.out.println("path：" + path + "resultPath：" + resultPath + "resultValue：" + resultValue);
//      json数组----------------------------------------------------------------
        JsonNode jsonNode1 = mapper.readTree(arrayJson);
        Iterator<JsonNode> elements = jsonNode1.elements();
        while (elements.hasNext()) {
            JsonNode node = elements.next();
            System.out.println(node.toString());
        }
//        取出所有key值为number的JsonNode的List
        List<JsonNode> findKeys = jsonNode1.findParents("number");
        for (JsonNode result : findKeys) {
            System.err.println(result.toString());
        }

    }

    @Test
    public void arryTest() {
        for (int i = 0; i < phpProgrammers.size(); i++) {
            if ("Quinn".equals(phpProgrammers.get(i).getFirstName())) {
                phpProgrammers.remove(i);
            }
        }
        phpProgrammers.forEach(person -> System.out.println("I：" + person.getFirstName()));
    }

    @Test
    public void intaaTest() {
        List<Object> list = new ArrayList<>();
        Map<Serializable, Object> map = new HashMap<Serializable, Object>() {
            {
                put("1", "String1");
                put("2", "String2");
            }
        };
        System.out.println("map的键：" + map.keySet());
        System.out.println("map的值：" + map.values());
        for (Serializable in : map.keySet()) {
            Object str = map.get(in);//得到每个key多对用value的值
            System.out.println(in + "   :  " + str);
        }
        for (Map.Entry<Serializable, Object> entry : map.entrySet()) {
            System.out.println("键 key ：" + entry.getKey() + " 值value ：" + entry.getValue());
            list.add(entry.getValue());
        }
        list.forEach(o -> System.out.println("list：" + o));
    }

    @Test
    public void userTest() {
        String shop = "Z999";
        char a = shop.charAt(shop.indexOf(shop.substring(0)));
        Integer num = new Integer(shop.substring(1, shop.length()));
        String b;
        if (num < 999) {
            num += 1;
            if (num < 10) {
                b = "00" + num.toString();
            } else if(num < 100) {
                b = "0" + num.toString();
            } else {
                b = num.toString();
            }
            System.out.println("最后结果：" + (a + b));
        } else {
            num = 1;
            b = "00" + num.toString();
            if(a == 90) {
                a = 64;
            }
            System.out.println("最后结果：" + ((char) (a + 1) + b));
        }
    }

    @Test
    public void enumTest() {
        int a = 2;
        String msg = EnumTest.msyByCode(2);
        System.out.println(msg);
        for (EnumTest e : EnumTest.values()) {
            System.out.println(e);
        }

        BigDecimal cc = new BigDecimal(10.02);
        BigDecimal dd = new BigDecimal(10.03);
        if(cc.compareTo(dd) == 1) {
            System.out.println("111111111111111111111111111111这是什么啊");
        } else if(cc.compareTo(dd) == -1) {
            System.out.println("-1-1-1-11-1-1-1-1-1-1-1-1-1-1-1-1-");
        }
    }

    @Test
    public void ioTest() {
        User user = new User();
        user.setId(1);
        user.setName("这是中文");
        user.setAge(1);
        String fileContent = "D:/aaa.txt";
        try {
            File file = new File(fileContent);
            if(!file.exists()) {
                file.createNewFile();
            }
            ObjectOutputStream os = new ObjectOutputStream(
                    new FileOutputStream(fileContent));
            os.writeObject(user); // 将User对象写进文件
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(
                    fileContent));
            user = (User) is.readObject(); // 从流中读取User的数据
            is.close();

            System.out.println("\nread after Serializable: ");
            System.out.println(user.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mapTest() {
        BigDecimal bigDecimal = new BigDecimal(3333);
        Integer integer = 222;
        int i = 22;
        Double dou = 23.22;
        bb(bigDecimal, integer, i, dou);
    }

    public static void bb(BigDecimal bigDecimal, Integer integer, int i, Double dou) {
        List<Integer> list = new ArrayList<>();
        list.add(bigDecimal.intValue());
        list.add(integer);
        list.add(dou.intValue());
        list.add(i);
        Collections.sort(list, (a, b) -> a.compareTo(b));
        list.forEach(integer1 -> System.out.println(integer1));
    }

}
