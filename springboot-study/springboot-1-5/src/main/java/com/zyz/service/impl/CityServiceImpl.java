package com.zyz.service.impl;

import com.zyz.dao.master.CityDao;
import com.zyz.domain.City;
import com.zyz.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 娃娃鱼
 * @date 2018/3/22 14:56
 */
@Service
public class CityServiceImpl implements CityService{

    @Autowired
    private CityDao cityDao;

    @Override
    public City findByName(String name) {
        return cityDao.findByName(name);
    }
}
