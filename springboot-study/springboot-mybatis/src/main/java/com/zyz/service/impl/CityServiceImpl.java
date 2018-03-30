package com.zyz.service.impl;

import com.zyz.dao.CityDao;
import com.zyz.domain.City;
import com.zyz.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 城市业务逻辑实现类
 *
 * Created by bysocket on 07/02/2017.
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<City> findAllCity(){
        return cityDao.findAllCity();
    }

    @Override
    public City findCityById(Integer id) {
        String key = "city_" + id;
        ValueOperations<String, City> operations = redisTemplate.opsForValue();
//        判断缓存中是否有数据
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            City city = operations.get(key);
            return city;
        }
        City city = cityDao.findById(id);
        // 插入缓存
        operations.set(key, city, 10, TimeUnit.SECONDS);
        return city;
    }

    @Override
    public Integer saveCity(City city) {
        return cityDao.saveCity(city);
    }

    @Override
    public Integer updateCity(City city) {
        // 先跟新数据库中的数据
        Integer ret = cityDao.updateCity(city);
        // 缓存存在，删除缓存
        String key = "city_" + city.getId();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
        }
        return ret;
    }

    @Override
    public Integer deleteCity(Integer id) {
        Integer rem = cityDao.deleteCity(id);
        String key = "city_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey) {
            redisTemplate.delete(key);
        }
        return rem;
    }

}
