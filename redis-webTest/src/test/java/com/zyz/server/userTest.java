package com.zyz.server;

import com.zyz.bean.User;
import com.zyz.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by 娃娃鱼 on 2018/3/6.
 */
public class userTest extends BaseTest {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testGet() {
//        System.out.println(userService.getById((long)1));
        User user = new User();
        user.setName("老八");
        user.setPhone("13588888888");
        user.setGender("m");
        userService.save(user);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setName("张三四");
        user.setId((long)1);
        userService.update(user);
    }

    @Test
    public void testRemove() {
        userService.removeById((long) 7);
    }

    @Test
    public void testRedis() {
        redisTemplate.delete("user-2");
    }

}
