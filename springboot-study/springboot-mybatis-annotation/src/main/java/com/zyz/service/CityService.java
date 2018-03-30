package com.zyz.service;

import com.zyz.domain.City;

/**
 * @author 娃娃鱼
 * @date 2018/3/29 9:49
 */
public interface CityService {

    /**
     * 根据城市名称，查询城市信息
     * @param cityName
     */
    City findCityByName(String cityName);

}
