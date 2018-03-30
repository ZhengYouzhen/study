package com.zyz.dao;

import com.zyz.domain.City;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author 娃娃鱼
 * @date 2018/3/29 9:40
 */
@Mapper
@Repository
public interface CityDao {

    /**
     * 根据城市名字搜索
     * @param cityName
     * @return
     */
    @Select("select * from city where city_name = #{cityName}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "provinceId", column = "province_id"),
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "description", column = "description"),
    })
    City findByName(@Param("cityName") String cityName);

}
