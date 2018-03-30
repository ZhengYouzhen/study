package com.zyz.dao;


import com.zyz.domain.City;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 城市 DAO 接口类
 *
 * Created by bysocket on 07/02/2017.
 */
@Repository
public interface CityDao {

    /**
     * 获取城市信息列表
     *
     * @return
     */
    List<City> findAllCity();

    /**
     * 根据城市 ID，获取城市信息
     *
     * @param id
     * @return
     */
    City findById(Integer id);

    Integer saveCity(City city);

    Integer updateCity(City city);

    Integer deleteCity(Integer id);
}
