package com.zyz.controller;

import com.zyz.bean.User;
import com.zyz.common.ControllerEnum;
import com.zyz.common.ControllerStatus;
import com.zyz.service.UserService;
import com.zyz.utils.EncryptUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("loginPage")
    public String loginPage() {
        return "user/login";
    }

    @PostMapping("login")
    @ResponseBody
    public ControllerStatus login(User user) {
        System.out.println("name:" + user.getName() + "----password:" + user.getPassword());
        ControllerStatus status = null;
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(user.getName(), EncryptUtils.md5(user.getPassword())));
            User user1 = userService.getByNamePwd(user.getName(), EncryptUtils.md5(user.getPassword()));
            Session session = subject.getSession();
            if(subject.hasRole("root")) {
                session.setAttribute("root", user1);
            }
            if(subject.hasRole("admin")) {
                session.setAttribute("admin", user1);
            }
            if(subject.hasRole("general")) {
                session.setAttribute("user", user1);
            }
            status = ControllerStatus.status(ControllerEnum.SUCCESS);
        } catch (UnknownAccountException e) {
            status = ControllerStatus.status(ControllerEnum.FAIL);
        } catch (IncorrectCredentialsException e) {
            status = ControllerStatus.status(ControllerEnum.FAIL);
        } catch (AuthenticationException e) {
            status = ControllerStatus.status(ControllerEnum.FAIL);
        }
        return status;
    }

    @RequestMapping("home")
    public String home() {
        return "user/home";
    }

    @RequestMapping("main")
    public String main() {
        return "user/main";
    }

    @RequestMapping("out")
    public String out(Session session) {
        session.removeAttribute("user");
        return "index";
    }

}
