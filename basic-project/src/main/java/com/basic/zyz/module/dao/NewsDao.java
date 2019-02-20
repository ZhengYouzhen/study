package com.basic.zyz.module.dao;

import com.basic.zyz.module.pojo.News;
import com.basic.zyz.common.basic.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.basic.zyz.module.pojo.News;


/**
 * 咨询管理DAO接口
 * @author zyzz
 * @version 2018-12-16
 */
@Mapper
@Repository
public interface NewsDao extends BaseDao<News> {
	
}