package com.basic.zyz.module.dao;

import com.basic.zyz.module.pojo.RoleMenu;
import com.basic.zyz.common.basic.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.basic.zyz.module.pojo.RoleMenu;


/**
 * 角色菜单DAO接口
 * @author zyz
 * @version 2018-12-18
 */
@Mapper
@Repository
public interface RoleMenuDao extends BaseDao<RoleMenu> {
	
}