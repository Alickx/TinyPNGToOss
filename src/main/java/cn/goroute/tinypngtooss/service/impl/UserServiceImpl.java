package cn.goroute.tinypngtooss.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.goroute.tinypngtooss.dao.TUserDao;
import cn.goroute.tinypngtooss.exception.ServiceException;
import cn.goroute.tinypngtooss.pojo.TUser;
import cn.goroute.tinypngtooss.pojo.model.PasswordUpdateModel;
import cn.goroute.tinypngtooss.pojo.model.UserLoginModel;
import cn.goroute.tinypngtooss.pojo.model.UserOssInfoModel;
import cn.goroute.tinypngtooss.pojo.model.UserRegisterModel;
import cn.goroute.tinypngtooss.service.UserService;
import cn.goroute.tinypngtooss.util.DescribeCaptchaResult;
import cn.goroute.tinypngtooss.util.encryption.MyEncryption;
import cn.goroute.tinypngtooss.util.resresult.RespResult;
import cn.goroute.tinypngtooss.util.resresult.Result;
import cn.hutool.core.date.DateTime;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Author Alickx
 * @Date 2021/10/26 8:51
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    TUserDao tUserDao;

    @Autowired
    DescribeCaptchaResult captchaResult;

    @Override
    public Result getOssInfo() {

        //获取当前登陆的id
        int loginId = StpUtil.getLoginIdAsInt();

        TUser user = tUserDao.selectById(loginId);

        if (user != null) {

            UserOssInfoModel ossInfoModel = new UserOssInfoModel();

            BeanUtils.copyProperties(user, ossInfoModel);

            return RespResult.success(ossInfoModel);

        } else {
            throw new ServiceException("用户信息获取失败，请联系管理员！");
        }

    }


    @Override
    public Result userLogin(UserLoginModel userLoginModel, HttpServletRequest request) {


        String accountName = userLoginModel.getAccountName();

        String accountPassword = userLoginModel.getAccountPassword();

        String ticket = userLoginModel.getTicket();

        String randstr = userLoginModel.getRandstr();

        String clientIp = ServletUtil.getClientIP(request);

        //核验验证码
        JSONObject tencentCaptchaResult = captchaResult.getTencentCaptchaResult(ticket, randstr, clientIp);

        int captchaCode = Integer.parseInt(tencentCaptchaResult.getString("CaptchaCode"));


        if (captchaCode != 1) {
            throw new ServiceException("验证码错误！");
        }


        //校验用户是否存在;
        TUser tUser = tUserDao.selectOne(new QueryWrapper<TUser>().eq("account_name", accountName));
        if (tUser == null) {
            throw new ServiceException("用户不存在！");
        }

        //校验密码,密码为MD5加密
        if (!Objects.equals(DigestUtil.md5Hex(accountPassword), tUser.getAccountPassword())) {
            throw new ServiceException("用户密码不正确！");
        }


        //获取token信息
        StpUtil.login(tUser.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();



        //校验成功
        return RespResult.success(tokenInfo);

    }


    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Result userRegister(UserRegisterModel userRegisterModel, HttpServletRequest request) {

        //用户名列
        String columnName = "account_name";

        String accountName = userRegisterModel.getAccountName();

        String accountPassword = userRegisterModel.getAccountPassword();

        String ticket = userRegisterModel.getTicket();

        String randstr = userRegisterModel.getRandstr();

        String clientIp = ServletUtil.getClientIP(request);
        //核验验证码
        JSONObject tencentCaptchaResult = captchaResult.getTencentCaptchaResult(ticket, randstr, clientIp);

        int captchaCode = Integer.parseInt(tencentCaptchaResult.getString("CaptchaCode"));


        if (captchaCode != 1) {
            throw new ServiceException("验证码错误！");
        }

        //验证是否存在用户,如果不存在则新增
        if (tUserDao.selectOne(new QueryWrapper<TUser>().eq("account_name", accountName)) == null) {
            TUser user = new TUser();
            user.setAccountName(accountName);
            user.setAccountPassword(DigestUtil.md5Hex(accountPassword));
            user.setCreateTime(new DateTime());
            int insert = tUserDao.insert(user);

            if (insert == 0) {
                throw new ServiceException("用户数据库注册失败，请联系管理员！");
            } else {
                return RespResult.success();
            }
        } else {
            throw new ServiceException("用户名已存在！");
        }
    }


    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Result userOssUpdate(UserOssInfoModel userOssInfoModel) {

        String accessId = userOssInfoModel.getAccessId();

        //对accessKey进行加密
        String accessKey = userOssInfoModel.getAccessKey();

        String encryptAccessKey = MyEncryption.encryptHex(accessKey);

        String dir = userOssInfoModel.getDir();

        String bucket = userOssInfoModel.getBucket();

        String endpoint = userOssInfoModel.getEndpoint();

        int loginId = StpUtil.getLoginIdAsInt();

        TUser user = new TUser();
        user.setAccessId(accessId)
                .setAccessKey(encryptAccessKey)
                .setDir(dir)
                .setBucket(bucket)
                .setEndpoint(endpoint);

        int result = tUserDao.update(user, new UpdateWrapper<TUser>().eq("id", loginId));

        if (result != 0) {
            return RespResult.success();
        } else {
            throw new ServiceException("用户Oss信息数据库更新失败，请联系管理员！");
        }


    }

    @Override
    public Result passwordUpdate(PasswordUpdateModel passwordUpdateModel) {

        //验证二次密码是否正确
        String accountPassword = passwordUpdateModel.getAccountPassword();

        String newAccountPassword = passwordUpdateModel.getNewAccountPassword();

        //验证用户密码是否正确
        int userId = StpUtil.getLoginIdAsInt();
        TUser user = tUserDao.selectById(userId);

        if (!Objects.equals(DigestUtil.md5Hex(accountPassword), user.getAccountPassword())) {
            throw new ServiceException("用户密码不正确！");
        }
        String newPassword = DigestUtil.md5Hex(newAccountPassword);
        user.setAccountPassword(newPassword);

        //修改密码
        int i = tUserDao.updateById(user);

        //判断结果
        if (i != 0) {
            StpUtil.logout(userId);
            return RespResult.success();
        } else {
            throw new ServiceException("用户密码更新失败，请联系管理员！");
        }
    }
}
