package cn.goroute.tinypngtooss.service;

import cn.goroute.tinypngtooss.pojo.TUser;
import cn.goroute.tinypngtooss.pojo.model.PasswordUpdateModel;
import cn.goroute.tinypngtooss.pojo.model.UserLoginModel;
import cn.goroute.tinypngtooss.pojo.model.UserOssInfoModel;
import cn.goroute.tinypngtooss.pojo.model.UserRegisterModel;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Alickx
 * @Date 2021/10/26 8:48
 */
public interface UserService {


    /**
     * 获取用户信息
     * @param loginId 用户Id
     * @return 用户对象
     */
     TUser info(int loginId);

    /**
     * 用户登陆逻辑处理
     * @param userLoginModel 用户登陆模型类
     * @param request
     * @return 用户对象
     */
    TUser userLogin(UserLoginModel userLoginModel, HttpServletRequest request);

    /**
     * 用户注册逻辑处理
     * @param userRegisterModel 用户注册模型类
     * @param request
     * @return 1=注册成功 0=注册失败
     */
    int userRegister(UserRegisterModel userRegisterModel, HttpServletRequest request);

    /**
     * 用户oss信息上传，更新
     * @param userOssInfoModel Oss信息模型类
     * @return 插入结果 true=成功 false=失败
     */
    int userOssUpdate(UserOssInfoModel userOssInfoModel);

    /**
     * 用户修改密码
     * @param passwordUpdateModel 修改密码模型
     * @return 修改结果
     */
    int passwordUpdate(PasswordUpdateModel passwordUpdateModel);
}
