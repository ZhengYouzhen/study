package com.zyz.service;

import com.zyz.bean.City;

/**
 * @author 娃娃鱼
 * @date 2018/3/30 15:27
 */
public interface CityService {

    int deleteByPrimaryKey(Integer id);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);

}
