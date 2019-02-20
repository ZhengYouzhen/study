package com.basic.zyz.module.dao;

import com.basic.zyz.module.pojo.Message;
import com.basic.zyz.common.basic.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.basic.zyz.module.pojo.Message;


/**
 * 反馈管理DAO接口
 * @author zyz
 * @version 2018-12-16
 */
@Mapper
@Repository
public interface MessageDao extends BaseDao<Message> {
	
}