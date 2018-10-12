package com.zyz.controller;

import com.zyz.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author zyz
 * @date 2018/7/26
 */
@RestController
@Api(value = "swagger的值", description = "swagger的描述")
@RequestMapping("/zyz/test")
public class SwaggerController {

    @ApiOperation(value = "根据id查询学生信息", notes = "查询数据库中某个的学生信息")
    @ApiImplicitParam(name = "id", value = "学生ID", paramType = "path", required = true, dataType = "integer")
    @GetMapping("getUser/{id}")
    public User getUser(@PathVariable int id) {
        User user = new User();
        user.setId(id);
        user.setAge(233);
        user.setName("测试用户");
        return user;
    }

    @ApiOperation("处理用户数据")
    @PostMapping("updateUser/{id}")
    public User updateUser(@PathVariable("id") Integer id, @RequestParam("name") String name) {
        User user = new User();
        user.setId(id);
        user.setAge(233);
        user.setName(name);
        return user;
    }


}
