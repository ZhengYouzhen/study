
package com.zyz.service;


import com.zyz.domain.City;

import java.util.List;

public interface CityService {

    /**
     * 新增城市信息
     *
     * @param city
     * @return
     */
    Integer saveCity(City city);

    /**
     * 根据关键词，function score query 权重分分页查询
     *
     * @param pageNumber
     * @param pageSize
     * @param searchContent
     * @return
     */
    List<City> searchCity(Integer pageNumber, Integer pageSize, String searchContent);
}