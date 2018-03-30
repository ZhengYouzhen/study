package com.zyz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 娃娃鱼
 * @date 2018/3/30 15:34
 */
@Controller
public class ThymeleafController {

    @RequestMapping("")
    public String index(ModelMap map) {
        map.addAttribute("test","testValue");
        map.addAttribute("test1","testValue1");
        return "index";
    }

}
