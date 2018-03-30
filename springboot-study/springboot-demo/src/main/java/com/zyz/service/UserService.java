package com.zyz.service;

import com.zyz.bean.User;

/**
 * @author 娃娃鱼
 * @date 2018/3/30 15:28
 */
public interface UserService {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
