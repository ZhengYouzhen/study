package com.zyz.controller;

import com.zyz.domain.City;
import com.zyz.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author 娃娃鱼
 * @date 2018/3/19 16:19
 */
@Controller
public class FreemarkerController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/api/findOneCity/{id}", method = RequestMethod.GET)
    public String findOneCity(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("city", cityService.findCityById(id));
        return "city";
    }

    @RequestMapping(value = "/api/findAllCity", method = RequestMethod.GET)
    public String findAllCity(Model model) {
        List<City> cityList = cityService.findAllCity();
        model.addAttribute("cityList",cityList);
        return "cityList";
    }

    @RequestMapping(value = "/api/test", method = RequestMethod.GET)
    public String test() {
        return "test";
    }

}
