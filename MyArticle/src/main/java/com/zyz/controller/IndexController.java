package com.zyz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 娃娃鱼 on 2017/12/9.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("")
    public String index() {
        return "index";
    }

}
