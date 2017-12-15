package com.zyz.service;

import com.zyz.bean.User;
import com.zyz.utils.EncryptUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 娃娃鱼 on 2017/12/12.
 */
public class UserTest extends BaseTest{

    @Autowired
    private UserService userService;

    @Test
    public void testSave() {
        User user = new User();
        user.setName("用户3");
        user.setPhone("987654321");
        user.setPassword(EncryptUtils.md5("123456"));
        userService.save(user);
    }

    @Test
    public void testSelect() {
        User user = userService.getByNamePwd("用户1","4QrcOUm6Wau+VuBX8g+IPg==");
    }

}
