package com.basic.zyz.module.service;

import java.util.List;

import com.basic.zyz.module.pojo.UserRole;
import com.basic.zyz.module.pojo.UserRole;
import com.github.pagehelper.PageInfo;

/**
 * 用户角色Service
 * @author zyz
 * @version 2018-12-18
 */
public interface UserRoleService {

	UserRole get(String id);

    UserRole get(UserRole userRole);

    List<UserRole> findList(UserRole userRole);

    PageInfo<UserRole> findPage(UserRole userRole);

    void save(UserRole userRole);

    void delete(UserRole userRole);
		
}