package com.zyz.controller;

import com.zyz.domain.City;
import com.zyz.service.CityService;
import com.zyz.task.ScheduledTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制层
 *
 * Created by bysocket on 07/02/2017.
 */
@RestController
public class CityRestController {

    @Autowired
    private CityService cityService;
    @Autowired
    private ScheduledTasks scheduledTasks;

    /**
     *根据城市名获取信息
     * @param
     * @return
     */
    @RequestMapping(value = "/api/city/{cityName}", method = RequestMethod.GET)
    public City findByName(@PathVariable String cityName) {
        scheduledTasks.reportCurrentTime();
        return cityService.findByName(cityName);
    }

}
