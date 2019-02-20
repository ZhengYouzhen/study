package com.basic.zyz.module.page;

import com.basic.zyz.module.pojo.Menu;
import com.basic.zyz.module.service.MenuService;
import com.basic.zyz.common.basic.BaseController;
import com.basic.zyz.common.constant.RedisEnum;
import com.basic.zyz.common.utils.RedisUtil;
import com.basic.zyz.module.pojo.Menu;
import com.basic.zyz.module.service.MenuService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by yao on 2018/12/18.
 */
@Controller
@RequestMapping("/page/menu")
public class MenuPgController extends BaseController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = {"", "list"})
    public String list() {
        return "home/menu/menuList";
    }

    @RequestMapping("menu")
    public String menu() {
        return "home/menu/menu";
    }

    @RequestMapping("save")
    public String save(Menu menu, Model model, RedirectAttributes redirectAttributes) {
//      更改菜单，把redis菜单结构清空清空
        redisUtil.del(RedisEnum.REDIS_MENU.getKey());
        menu.setCreateBy(getUserInfo().getId());
        menu.setUpdateBy(getUserInfo().getId());
        menuService.save(menu);
        redirectAttributes.addFlashAttribute("msg", "保存菜单成功！");
        return "redirect:/page/menu";
    }

    /**
     * 添加，修改
     * @param menu
     * @param model
     * @return
     */
    @RequestMapping("form")
    public String form(Menu menu, Model model) {
//        查询菜单信息
        if (StringUtils.isNotBlank(menu.getId())) {
            menu = menuService.get(menu.getId());
        }
//        父级菜单列表，父级菜单以选的形式展示
        List<Menu> menuList = Lists.newArrayList();
//        父级菜单查询
        Menu menuQuery = new Menu();
        if (StringUtils.isBlank(menu.getParentId()) || Menu.PARENT_ID.equals(menu.getParentId())) {
            menuQuery.setId(Menu.PARENT_ID);
            menuQuery.setName("一级菜单");
            menuList.add(menuQuery);
        } else {
//            这里条件给的不严谨，后期修改
            menuQuery.setParentId(Menu.PARENT_ID);
            menuList = menuService.findList(menuQuery);
        }
        model.addAttribute("menu", menu);
        model.addAttribute("menuList", menuList);
        return "home/menu/menuForm";
    }

    /**
     * 添加下级
     */
    @RequestMapping("addChild")
    public String addChild(Menu menu, Model model, RedirectAttributes redirectAttributes) {
        menu = menuService.get(menu.getId());
        if (menu == null) {
            redirectAttributes.addFlashAttribute("msg", "菜单不存在！");
            return "redirect:/page/menu";
        }
        //        父级菜单查询
        Menu menuQuery = new Menu();
        menuQuery.setParentId(menu.getParentId());
        //        父级菜单列表，父级菜单以选的形式展示
        List<Menu> menuList = menuService.findList(menuQuery);
        menuQuery.setParentId(menu.getId());
        model.addAttribute("menu", menuQuery);
        model.addAttribute("menuList", menuList);
        return "home/menu/menuForm";
    }
}
