package com.zyz.redis;

import com.zyz.bean.User;
import com.zyz.server.BaseTest;
import com.zyz.utils.RedisCommand;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 娃娃鱼 on 2018/3/7.
 */
public class RedisTest extends BaseTest {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    private RedisCommand redisHandle;

    @Test
    public void testUserInfo() {
        redisTemplate.execute(new RedisCallback<Integer>() {
            public Integer doInRedis(RedisConnection connection) {
                int i = 0;
                for (; i < 100; i++) {
                    byte[] key = ("key:" + i).getBytes();
                    byte[] value = ("value:" + i).getBytes();
                    connection.set(key, value);
                }
                //这里返回值是个上面的RedisCallback<Integer> 中的泛型一直，
                return i;

            }
        });
    }

    @Test
    public void testGet() {
        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
        System.out.println(valueOperations.get("user-1"));
//        System.out.println(redisTemplate.getExpire("key1"));
    }

    @Test
    public void testAll() {
        List<User> users = (List <User>)redisHandle.get("userList");
        for(User user : users) {
            System.out.println(user.getName());
        }
    }

    @Test
    public void testList() {
        ListOperations<String, Object> listOperations = redisTemplate.opsForList();
        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setName("水木" + i);
            user.setPhone("123456765666");
            user.setGender("男");
            listOperations.leftPush("name1", user);
            listOperations.rightPush("name2", user);
        }
        // 可给数据排序
        User user1 = (User) listOperations.leftPop("name1");
        User user2 = (User) listOperations.rightPop("name2");
        System.out.println(user1.toString());
        System.out.println(user2.toString());
    }

    @Test
    public void testSet() {
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setName("花花" + i);
            user.setPhone("123456765666");
            user.setGender("女");
            setOperations.add("flowers", user);
        }
        User user = (User) setOperations.pop("flowers");
        System.out.println(user.getName() + user.getPhone() + user.getGender());
    }

    @Test
    public void testZSet() {
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setName("setValue" + i);
            user.setPhone("9123456789");
            user.setGender("男");
            zSetOperations.add("zset", user, i);
            System.out.println(zSetOperations.range("zset", 1, 2));
            System.out.println(zSetOperations.rangeByScore("zset", 1, 2));
            System.out.println();
        }
    }

    @Test
    public void testHash() {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        Map<String, String> map = new HashMap();
        map.put("map1", "mapvalue1");
        map.put("map2", "mapvalue2");
        hashOperations.putAll("allmap", map);
        System.out.println(hashOperations.entries("allmap"));
    }

}
