package com.basic.zyz.module.controller;

import com.basic.zyz.common.constant.BaseModel;
import com.basic.zyz.common.constant.MsgModel;
import com.basic.zyz.module.pojo.Menu;
import com.basic.zyz.module.pojo.User;
import com.basic.zyz.module.service.MenuService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by yao on 2018/12/18.
 */
@RestController
@RequestMapping("data")
@Api(value = "MenuController", description = "系统菜单添加、查询、更新、冻结")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("menu/list")
    @ApiOperation("菜单列表")
    public BaseModel list() {
        Menu menu = new Menu();
        menu.setOrderBy("a.parent_Id, a.sort");
        return new BaseModel("0","ok", menuService.findList(menu));
    }

    @RequestMapping(value = "menu/add", method = RequestMethod.POST)
    @ApiOperation("菜单添加")
    public MsgModel add(@RequestBody Menu menu, HttpSession session) {
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            menu.setCreateBy(user.getId());
            menu.setUpdateBy(user.getId());
        }
        menuService.save(menu);
        return MsgModel.ok();
    }

    @PostMapping("menu/update")
    @ApiOperation("菜单修改信息")
    public MsgModel update(@RequestBody Menu menu) {
        menuService.save(menu);
        return MsgModel.ok();
    }


    @GetMapping("menu/get" )
    @ApiOperation("获取菜单数据")
    @ResponseBody
    public MsgModel getDate(Menu menu,HttpSession session){
//        if (session.getAttribute("user") != null) {
//            User user = (User) session.getAttribute("user");
//        }
        PageInfo<Menu> menuDate=menuService.findPage(menu);
        return new MsgModel("0000", "查询成功！", menuDate.getList(), menuDate.getTotal());
    }
}
