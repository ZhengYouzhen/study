package com.zyz.dao;

import com.zyz.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper{

    User getByNamePwd(@Param("name") String name, @Param("password") String password);

}