package com.basic.zyz.module.page;

import com.basic.zyz.module.pojo.Menu;
import com.basic.zyz.module.service.MenuService;
import com.basic.zyz.common.constant.Constants;
import com.basic.zyz.common.constant.RedisEnum;
import com.basic.zyz.common.constant.TreeModel;
import com.basic.zyz.common.utils.RedisUtil;
import com.basic.zyz.module.pojo.Menu;
import com.basic.zyz.module.service.MenuService;
import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author zyz
 * @date 2018/9/14
 */
@Controller
@RequestMapping("/")
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private MenuService menuService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping({"", "index"})
    public String page() {
        return "index";
    }

    /**
     * 退出登录，也可以通过ajax的形式调用logout，需要自定义
     *
     * @return
     */
    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:index";
    }

    /**
     * 如果不使用ajax登录，则使用以下方法（需要改shiro的配置代码）
     *
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("login")
    public String login(HttpServletRequest request, Map<String, Object> map) {

        LOGGER.info("IndexController.login");
        Object exception = request.getAttribute("shiroLoginFailure");
        String msg = "";
        if (exception != null) {
            LOGGER.error("异常:{}", exception);
            msg = exception.toString();
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理.
        return "login";
    }

    @RequestMapping("home")
    public String home(Model model) {
//        组装成树结构的集合，从redis中获取，如果没有获取到则从数据库中获取
        @SuppressWarnings("unchecked")
        List<TreeModel<Menu>> menuTree = (List<TreeModel<Menu>>) redisUtil.get(RedisEnum.REDIS_MENU.getKey());
        if (menuTree == null) {
            Menu menu = new Menu();
            menu.setIsShow(Constants.YES);
//        先把所有未隐藏的菜单查出
            List<Menu> menus = menuService.findList(menu);
            menuTree = Lists.newArrayList();
            for (Menu menuData : menus) {
//                第一层必须父id为0
                if (Menu.PARENT_ID.equals(menuData.getParentId())) {
                    TreeModel<Menu> node = new TreeModel<>();
                    node.setNode(menuData);
//                    插入下一层菜单：当前菜单数据，全部菜单数据
                    node.setChildren(childMenu(node, menus));
                    menuTree.add(node);
                }
            }
            redisUtil.set(RedisEnum.REDIS_MENU.getKey(), menuTree);
        }
        model.addAttribute("menuTree", menuTree);
        return "home/home";
    }

    private List<TreeModel<Menu>> childMenu(TreeModel<Menu> parent, List<Menu> menuList) {
//        做法基本与上一致
        List<TreeModel<Menu>> children = Lists.newArrayList();
        if (!menuList.isEmpty()) {
            for (Menu menu : menuList) {
//                寻找出父级菜单ID与parent菜单ID相同的菜单
                if (menu.getParentId().equals(parent.getNode().getId())) {
//                    这里暂时只做两层菜单，如果以后做四级五级等等，则在此递归......
                    TreeModel<Menu> node = new TreeModel<>();
                    node.setNode(menu);
//                    这里暂时不做递归，因为菜单只有两层
//                    node.setChildren(childMenu(node, menuList));
                    // 加入子节点
                    children.add(node);
                }
            }
        }
        return children;
    }

}
