package com.basic.zyz.module.dao;

import com.basic.zyz.module.pojo.Dict;
import com.basic.zyz.common.basic.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.basic.zyz.module.pojo.Dict;


/**
 * 字典管理DAO接口
 * @author zyz
 * @version 2019-01-07
 */
@Mapper
@Repository
public interface DictDao extends BaseDao<Dict> {
	
}