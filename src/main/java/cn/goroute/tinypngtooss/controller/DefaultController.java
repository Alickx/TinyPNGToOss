package cn.goroute.tinypngtooss.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author Alickx
 * @Date 2021/10/31 19:40
 */
@Controller
public class DefaultController {

    @GetMapping("/user/login")
    public String login(){
        if (!StpUtil.isLogin()) {
        return "login";
        } else {
            return "redirect:/index";
        }
    }

    @GetMapping("/user/register")
    public String register(){
        if (!StpUtil.isLogin()) {
            return "register";
        } else {
            return "redirect:/index";
        }
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @SaCheckLogin
    @GetMapping("/user/info")
    public String updateUserOss(){
        return "userinfo";
    }




}
