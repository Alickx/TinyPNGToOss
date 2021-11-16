package cn.goroute.tinypngtooss.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.goroute.tinypngtooss.pojo.model.PasswordUpdateModel;
import cn.goroute.tinypngtooss.pojo.model.UserLoginModel;
import cn.goroute.tinypngtooss.pojo.model.UserOssInfoModel;
import cn.goroute.tinypngtooss.pojo.model.UserRegisterModel;
import cn.goroute.tinypngtooss.service.UserService;
import cn.goroute.tinypngtooss.util.resresult.RespResult;
import cn.goroute.tinypngtooss.util.resresult.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Author Alickx
 * @Date 2021/10/25 23:05
 * 用户登陆和注册
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/user/login")
    public Result login(@RequestBody @Valid UserLoginModel userLoginModel, HttpServletRequest request) {
        return userService.userLogin(userLoginModel, request);
    }



    @PostMapping("/user/register")
    public Result register(@RequestBody @Valid UserRegisterModel userRegisterModel,HttpServletRequest request) {
        return userService.userRegister(userRegisterModel,request);
    }

    @SaCheckLogin
    @RequestMapping("/user/AliossUpdate")
    public Result update(@RequestBody UserOssInfoModel userOssInfoModel) {

        return userService.userOssUpdate(userOssInfoModel);
    }

    @SaCheckLogin
    @RequestMapping("/user/passwordChange")
    public Result passwordUpdate(@RequestBody @Valid PasswordUpdateModel passwordUpdateModel) {

        return userService.passwordUpdate(passwordUpdateModel);
    }

    @SaCheckLogin
    @GetMapping("/user/getOssInfo")
    public Result info() {
         return userService.getOssInfo();
    }

    @SaCheckLogin
    @GetMapping("/user/logout")
    public Result logOut() {

        //登出注销
        StpUtil.logout(StpUtil.getLoginId());

        //返回成功响应
        return RespResult.success();
    }

    @GetMapping("/user/isLogin")
    public Result isLogin(){
        //检查是否登陆了
        return RespResult.success(StpUtil.getTokenInfo());

    }

}
