package com.basic.zyz.module.service;

import java.util.List;

import com.basic.zyz.module.pojo.RoleMenu;
import com.github.pagehelper.PageInfo;

/**
 * 角色菜单Service
 * @author zyz
 * @version 2018-12-18
 */
public interface RoleMenuService {

	RoleMenu get(String id);

    RoleMenu get(RoleMenu roleMenu);

    List<RoleMenu> findList(RoleMenu roleMenu);

    PageInfo<RoleMenu> findPage(RoleMenu roleMenu);

    void save(RoleMenu roleMenu);

    void delete(RoleMenu roleMenu);
		
}