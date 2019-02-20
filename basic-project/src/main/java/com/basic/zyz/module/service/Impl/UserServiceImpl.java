package com.basic.zyz.module.service.Impl;

import com.basic.zyz.common.basic.BaseService;
import com.basic.zyz.module.dao.RoleDao;
import com.basic.zyz.module.dao.UserDao;
import com.basic.zyz.module.pojo.Role;
import com.basic.zyz.module.pojo.User;
import com.basic.zyz.module.service.UserService;
import com.basic.zyz.module.dao.RoleDao;
import com.basic.zyz.module.dao.UserDao;
import com.basic.zyz.module.pojo.Role;
import com.basic.zyz.module.pojo.User;
import com.basic.zyz.common.basic.BaseService;
import com.basic.zyz.module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户管理ServiceImpl
 * @author zyz
 * @version 2018-12-16
 */
@Service
public class UserServiceImpl extends BaseService<UserDao, User> implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public String getId() {
        return "1";
    }

    @Override
    public int updStateById(String id,String state) {
        return userDao.updStateById(id,state);
    }

    @Override
    public User findUserByPhone(String phone) {
        return userDao.getUserByphone(phone);
    }

    @Override
    public List<Role> findListByUserId(String phone) {
        User user = userDao.getUserByphone(phone);
        Role role = new Role();
        List<Role> roleList = roleDao.queryList(role);
        return roleList;
    }


}