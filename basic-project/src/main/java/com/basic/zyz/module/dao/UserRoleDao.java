package com.basic.zyz.module.dao;

import com.basic.zyz.module.pojo.UserRole;
import com.basic.zyz.common.basic.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.basic.zyz.module.pojo.UserRole;


/**
 * 用户角色DAO接口
 * @author zyz
 * @version 2018-12-18
 */
@Mapper
@Repository
public interface UserRoleDao extends BaseDao<UserRole> {
	
}