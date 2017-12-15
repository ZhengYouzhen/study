package com.zyz.controller;

import com.zyz.bean.Type;
import com.zyz.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 娃娃鱼 on 2017/12/13.
 */
@Controller
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping("all")
    @ResponseBody
    public List<Type> allType() {
        List<Object> objects = typeService.listAll();
        List<Type> types = new ArrayList<>();
        for (Object obj : objects) {
            Type type = (Type) obj;
            types.add(type);
        }
        return types;
    }

}
