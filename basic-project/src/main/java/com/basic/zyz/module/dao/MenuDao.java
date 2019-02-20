package com.basic.zyz.module.dao;

import com.basic.zyz.module.pojo.Menu;
import com.basic.zyz.common.basic.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.basic.zyz.module.pojo.Menu;


/**
 * 菜单管理DAO接口
 * @author zyz
 * @version 2018-12-18
 */
@Mapper
@Repository
public interface MenuDao extends BaseDao<Menu> {
	
}