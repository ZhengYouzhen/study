package com.basic.zyz.module.service;

import java.util.List;

import com.basic.zyz.module.pojo.Role;
import com.github.pagehelper.PageInfo;

/**
 * 角色管理Service
 * @author zyz
 * @version 2018-12-18
 */
public interface RoleService {

	Role get(String id);

    Role get(Role role);

    List<Role> findList(Role role);

    PageInfo<Role> findPage(Role role);

    void save(Role role);

    void delete(Role role);
		
}