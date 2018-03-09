package com.zyz.service.impl;

import com.zyz.bean.User;
import com.zyz.common.Pager;
import com.zyz.dao.UserDAO;
import com.zyz.service.UserService;
import com.zyz.utils.RedisCommand;
import com.zyz.utils.RedisHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisCommand redisHandle;

    @Override
    public void save(Object obj) {
        ValueOperations<String, List<Object>> valueOperationsAll = redisTemplate.opsForValue();
        userDAO.save(obj);
        valueOperationsAll.set("userList",userDAO.listAll());
    }

    @Override
    public void remove(Object obj) {
        User user = (User) obj;
        redisHandle.remove("user-"+user.getId());
    }

    @Override
    public void removeById(Long id) {
        ValueOperations<String, List<Object>> valueOperationsAll = redisTemplate.opsForValue();
        userDAO.removeById(id);
//        redisTemplate.delete("user-" + id);
        redisHandle.remove("user-"+id);
        valueOperationsAll.set("userList",userDAO.listAll());
    }

    @Override
    public void update(Object obj) {
        User user1 = (User) obj;
        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
        ValueOperations<String, List<Object>> valueOperationsAll = redisTemplate.opsForValue();
        User user = valueOperations.get("user-" + user1.getId());
        // 如果缓存中有数据，则直接返回结果
        if (user != null) {
            userDAO.update(obj);
            valueOperations.set("user-" + user1.getId(), (User) userDAO.getById(user1.getId()));
            valueOperationsAll.set("userList",userDAO.listAll());
        }
    }

    @Override
    public Object getById(Long id) {
        // 第一步，从缓存中拿数据
        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
//        User user = valueOperations.get("user-" + id);
        User user = (User) redisHandle.get("user-"+id);
        // 如果缓存中有数据，则直接返回结果
        if (user != null) {
            System.out.println("缓存中拿数据");
            return user;
        } else {
            // 如果缓存中没有数据，则先去数据库中查找数据，然后缓存到缓存中，再返回结果
            Object obj = userDAO.getById(id);
            if (obj != null){
//                valueOperations.set("user-" + id, (User) obj);
                redisHandle.set("user-" + id, obj);
            }
            System.out.println("数据库中拿数据");
            return obj;
        }
    }

    @Override
    public List<Object> listAll() {
        // 第一步，从缓存中拿数据
        ValueOperations<String, List<User>> valueOperations = redisTemplate.opsForValue();
//        List<User> users = valueOperations.get("userList");
        List<User> users = (List <User>)redisHandle.get("userList");
        // 如果缓存中有数据，则直接返回结果
        if (users != null && users.size() != 0) {
            List<Object> objects = new ArrayList<>();
            for(User user : users) {
                objects.add(user);
            }
            System.out.println("缓存中拿数据");
            return objects;
        } else {
            // 如果缓存中没有数据，则先去数据库中查找数据，然后缓存到缓存中，再返回结果
            List<Object> objs = userDAO.listAll();
            List<User> users1 = new ArrayList<>();
            if (objs != null || objs.size() != 0){
                for(Object obj : objs) {
                    User user = (User) obj;
                    users1.add(user);
                }
                valueOperations.set("userList", users1);
            }
            System.out.println("数据库中拿数据");
            return objs;
        }
    }

    @Override
    public Pager listPager(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public Pager listPagerCriteria(int pageNo, int pageSize, Object obj) {
        return null;
    }
}
