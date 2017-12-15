package com.zyz.service.impl;

import com.zyz.bean.User;
import com.zyz.common.Pager;
import com.zyz.dao.UserMapper;
import com.zyz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 娃娃鱼 on 2017/12/12.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(Object obj) {
        userMapper.save(obj);
    }

    @Override
    public void remove(Object obj) {
userMapper.remove(obj);
    }

    @Override
    public void removeById(Long id) {
userMapper.removeById(id);
    }

    @Override
    public void update(Object obj) {
userMapper.update(obj);
    }

    @Override
    public Object getById(Long id) {
        return userMapper.getById(id);
    }

    @Override
    public List<Object> listAll() {
        return null;
    }

    @Override
    public Pager listPager(int pageNo, int pageSize) {
        Pager pager = new Pager(pageNo, pageSize);
        pager.setRows(userMapper.listPager(pager));
        pager.setTotal(userMapper.count());
        return pager;
    }

    @Override
    public User getByNamePwd(String name, String password) {
        return userMapper.getByNamePwd(name, password);
    }
}
