package com.zyz.controller;

import com.zyz.bean.User;
import com.zyz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("get/{id}")
    @ResponseBody
    public User get(@PathVariable("id") Long id) {
        Object obj = userService.getById(id);
        return obj == null ? null : (User) obj;
    }

    @GetMapping("all")
    @ResponseBody
    public List<User> all() {
        List<Object> objects = userService.listAll();
        List<User> users = new ArrayList<>();
        for(Object obj : objects) {
            User user = (User) obj;
            users.add(user);
        }
        return users;
    }

}
