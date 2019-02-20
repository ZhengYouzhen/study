package com.basic.zyz.module.service;

import java.util.List;

import com.basic.zyz.module.pojo.Role;
import com.basic.zyz.module.pojo.User;
import com.github.pagehelper.PageInfo;

/**
 * 用户管理Service
 * @author zyz
 * @version 2018-12-16
 */
public interface UserService {

	User get(String id);

    User get(User user);

    List<User> findList(User user);

    PageInfo<User> findPage(User user);

    void save(User user);

    void delete(User user);

    String getId();

    int updStateById(String id,String state);

    User findUserByPhone(String phone);

    List<Role> findListByUserId(String phone);
		
}