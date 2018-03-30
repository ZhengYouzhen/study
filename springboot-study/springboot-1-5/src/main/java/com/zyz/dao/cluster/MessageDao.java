package com.zyz.dao.cluster;

import com.zyz.domain.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 娃娃鱼
 * @date 2018/3/22 14:34
 */
@Mapper
public interface MessageDao {

    /**
     * 姓名属性
     * @param name
     * @return
     */
    Message findByName(@Param("name") String name);

}
