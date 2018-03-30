package com.zyz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 娃娃鱼
 * @date 2018/3/15 16:24
 */
@RestController
public class HelloController {

    @RequestMapping("/")
    public String hello() {
        return "hello,springboot";
    }

}
