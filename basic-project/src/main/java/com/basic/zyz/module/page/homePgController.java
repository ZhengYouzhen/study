package com.basic.zyz.module.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yao on 2018/12/22.
 */
@Controller
@RequestMapping("/page/home")
public class homePgController {

    @RequestMapping("main")
    public String main(){
        return "home/main";
    }
}
