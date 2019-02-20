package com.basic.zyz.module.service;

import java.util.List;

import com.basic.zyz.module.pojo.Menu;
import com.github.pagehelper.PageInfo;

/**
 * 菜单管理Service
 * @author zyz
 * @version 2018-12-18
 */
public interface MenuService {

	Menu get(String id);

    Menu get(Menu menu);

    List<Menu> findList(Menu menu);

    PageInfo<Menu> findPage(Menu menu);

    void save(Menu menu);

    void delete(Menu menu);
		
}