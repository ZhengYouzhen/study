package com.zyz.service;

import com.zyz.domain.City;

/**
 * @author 娃娃鱼
 * @date 2018/3/22 14:54
 */
public interface CityService {

    /**
     * 根据名字搜索
     * @param name
     * @return
     */
    City findByName(String name);

}
