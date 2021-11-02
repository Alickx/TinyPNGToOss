package cn.goroute.tinypngtooss.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.goroute.tinypngtooss.pojo.TUser;
import cn.goroute.tinypngtooss.pojo.model.PasswordUpdateModel;
import cn.goroute.tinypngtooss.pojo.model.UserLoginModel;
import cn.goroute.tinypngtooss.pojo.model.UserOssInfoModel;
import cn.goroute.tinypngtooss.pojo.model.UserRegisterModel;
import cn.goroute.tinypngtooss.pojo.vo.UserVo;
import cn.goroute.tinypngtooss.service.UserService;
import cn.goroute.tinypngtooss.util.resresult.RespResult;
import cn.goroute.tinypngtooss.util.resresult.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Author Alickx
 * @Date 2021/10/25 23:05
 * 用户登陆和注册
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/login")
    public Result login(@RequestBody @Validated UserLoginModel userLoginModel, HttpServletRequest request) {

        // 进行登陆处理
        TUser user = userService.userLogin(userLoginModel, request);

        //根据记住我，登陆
        StpUtil.login(user.getId(), userLoginModel.getRememberMe() != null);

        UserVo userVo = new UserVo();

        BeanUtils.copyProperties(user, userVo);

        //获取会话session
        SaSession session = StpUtil.getSession();

        //把oss配置存进session
        session.set("userOss", userVo);

        return RespResult.success("用户登陆成功！");

    }


    @PostMapping("/register")
    public Result register(@RequestBody @Valid UserRegisterModel userRegisterModel, HttpServletRequest request) {

        int registerResult = userService.userRegister(userRegisterModel, request);

        if (registerResult == 1) {
            return RespResult.success("用户注册成功！");
        } else {
            return RespResult.fail("用户注册失败！");
        }

    }

    @SaCheckLogin
    @RequestMapping("/ossUpdate")
    public Result update(@RequestBody UserOssInfoModel userOssInfoModel) {

        int updateResult = userService.userOssUpdate(userOssInfoModel);


        if (updateResult == 1) {
            return RespResult.success("用户Oss信息更新成功！");
        } else {
            return RespResult.fail("用户Oss信息更新失败！");
        }
    }

    @SaCheckLogin
    @RequestMapping("/passwordUpdate")
    public Result passwordUpdate(@RequestBody PasswordUpdateModel passwordUpdateModel) {

        int updateResult = userService.passwordUpdate(passwordUpdateModel);

        if (updateResult == 1) {
            StpUtil.logout(StpUtil.getLoginId());
            return RespResult.success("用户密码修改成功！");
        } else {
            return RespResult.fail("用户密码修改失败！");
        }
    }

    @SaCheckLogin
    @PostMapping("/info")
    public Result info() {

        int loginId = StpUtil.getLoginIdAsInt();

        TUser user = userService.info(loginId);
        UserVo userVo = new UserVo();

        BeanUtils.copyProperties(user, userVo);

        return RespResult.success(userVo);

    }

    @SaCheckLogin
    @GetMapping("/logout")
    public Result logOut() {

        //登出注销
        StpUtil.logout(StpUtil.getLoginId());

        //返回成功响应
        return RespResult.success("用户注销成功！");

    }

}
