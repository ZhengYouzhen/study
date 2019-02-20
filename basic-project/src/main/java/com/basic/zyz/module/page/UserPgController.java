package com.basic.zyz.module.page;

import com.basic.zyz.module.pojo.User;
import com.basic.zyz.module.service.UserService;
import com.basic.zyz.common.basic.BaseController;
import com.basic.zyz.common.utils.SecretUtils;
import com.basic.zyz.module.pojo.User;
import com.basic.zyz.module.service.UserService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by yao on 2018/12/21.
 */
@Controller
@RequestMapping("/page/user")
public class UserPgController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"", "list"})
    public String userList(User user, Model model) {
        PageInfo<User> page = userService.findPage(user);
        model.addAttribute("page", page);
        model.addAttribute("user", user);
        return "home/user/userList";
    }

    @RequestMapping("form")
    public String form(User user, Model model) {
        if (StringUtils.isNotBlank(user.getId())) {
            user = userService.get(user.getId());
        }
        model.addAttribute("user", user);
        return "home/user/userForm";
    }

    @RequestMapping("userInfo")
    public String userInfo(Model model) {
//        当前用户信息，这里先做测试，为完成
        User user = userService.get("1");
        model.addAttribute("user", user);
        return "home/user/userInfo";
    }

    @RequestMapping("save")
    public String save(User user, Model model, RedirectAttributes redirectAttributes) {
        if (StringUtils.isNotBlank(user.getPassword())) {
//        密码加密
            user.setPassword(SecretUtils.entryptPassword(user.getPassword()));
        }
        if (StringUtils.isBlank(user.getId())) {
            User userQuery = new User();
            userQuery.setPhone(user.getPhone());
            Object object = userService.get(userQuery);
            if (object != null) {
                model.addAttribute("msg", "手机号已经存在");
                return form(user, model);
            }
        }
        user.setCreateBy(getUserInfo().getId());
        user.setUpdateBy(getUserInfo().getId());
        redirectAttributes.addFlashAttribute("msg", "保存成功！");
        userService.save(user);
        return "redirect:/page/user";
    }

    @RequestMapping("delete")
    public String delete(User user, RedirectAttributes redirectAttributes) {
        userService.delete(user);
        redirectAttributes.addFlashAttribute("msg", "删除成功！");
        return "redirect:/page/user";
    }
    @RequestMapping("pwdUpdPg")
    public String pwdUpdate(){
        return "home/user/changePwd";
    }
    @RequestMapping("pwdUpd")
    public String pwdUpdate(@RequestParam("id") String id, @RequestParam("oldPwd") String oldPwd, @RequestParam("newPwd") String newPwd, Model model, HttpSession session){
        if(StringUtils.isNotBlank(oldPwd)&&StringUtils.isNotBlank(newPwd)){
            User user = (User) session.getAttribute("user");
            if(!SecretUtils.validatePassword(oldPwd, user.getPassword())){
                 model.addAttribute("error","原密码错误");
            }else {
                if(!oldPwd.equals(newPwd)){
                    User u = new User();
                    u.setId(id);
                    u.setPassword(SecretUtils.entryptPassword(newPwd));
                    userService.save(u);
                    model.addAttribute("msg", "密码修改成功，请重新登录！");
                    session.removeAttribute("user");//移除session用户信息
                }else{
                    model.addAttribute("error","修改密码和原密码一致！");
                }
            }
        }
        return "home/user/changePwd";
    }
    @RequestMapping("/personMsg")
    public String personMsg(){
        return "/home/user/personMsg";
    }
}
