package cn.goroute.tinypngtooss.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.goroute.tinypngtooss.dao.TUserDao;
import cn.goroute.tinypngtooss.exception.ServiceException;
import cn.goroute.tinypngtooss.pojo.TUser;
import cn.goroute.tinypngtooss.pojo.model.PasswordUpdateModel;
import cn.goroute.tinypngtooss.pojo.model.UserLoginModel;
import cn.goroute.tinypngtooss.pojo.model.UserOssInfoModel;
import cn.goroute.tinypngtooss.pojo.model.UserRegisterModel;
import cn.goroute.tinypngtooss.service.UserService;
import cn.hutool.core.date.DateTime;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @Override
    public TUser info(int loginId) {

        TUser user = tUserDao.selectById(loginId);

        if (user!=null) {
            return user;
        } else {
            throw new ServiceException("用户信息获取失败，请联系管理员！");
        }

    }

    @Override
    public TUser userLogin(UserLoginModel userLoginModel, HttpServletRequest request) {

        //用户名列
        String columnName = "account_name";

        //获取Session
        HttpSession session = request.getSession();

        //在Session域对象中获取验证码
        String requestVerificationCode = (String) session.getAttribute("verificationCode");

        //删除Session中的验证码
        session.removeAttribute("verificationCode");

        String accountName = userLoginModel.getAccountName();

        String accountPassword = userLoginModel.getAccountPassword();

        String verificationCode = userLoginModel.getVerificationCode();

        //校验验证码
        if (!Objects.equals(requestVerificationCode, verificationCode)) {
            throw new ServiceException("用户验证码不正确！");
        }

        //校验用户是否存在;
        TUser tUser = tUserDao.selectOne(new QueryWrapper<TUser>().eq(columnName, accountName));
        if (tUser == null) {
            throw new ServiceException("用户不存在！");
        }

        //校验密码,密码为MD5加密
        if (!Objects.equals(DigestUtil.md5Hex(accountPassword), tUser.getAccountPassword())) {
            throw new ServiceException("用户密码不正确！");
        }

        //校验成功
        return tUser;

    }


    @Transactional(rollbackFor = {Exception.class})
    @Override
    public int userRegister(UserRegisterModel userRegisterModel, HttpServletRequest request) {

        //获取Session
        HttpSession session = request.getSession();

        //在Session域对象中获取验证码
        String requestVerificationCode = (String) session.getAttribute("verificationCode");

        //删除Session中的验证码
        session.removeAttribute("verificationCode");

        //用户名列
        String columnName = "account_name";

        String accountName = userRegisterModel.getAccountName();

        String accountPassword = userRegisterModel.getAccountPassword();

        String accountPasswordAgain = userRegisterModel.getAccountPasswordAgain();

        //校验二次密码是否相同
        if (!accountPassword.equals(accountPasswordAgain)) {

            throw new ServiceException("用户二次密码不正确");
        }
        //校验验证码是否正确
        if (!Objects.equals(requestVerificationCode, userRegisterModel.getVerificationCode())) {
            throw new ServiceException("用户验证码不正确！");
        }

        //验证是否存在用户,如果不存在则新增
        if (tUserDao.selectOne(new QueryWrapper<TUser>().eq(columnName, accountName)) == null) {
            TUser user = new TUser();
            user.setAccountName(accountName);
            user.setAccountPassword(DigestUtil.md5Hex(accountPassword));
            user.setCreateTime(new DateTime());
            int insert = tUserDao.insert(user);

            if (insert == 0) {
                throw new ServiceException("用户数据库注册失败，请联系管理员！");
            } else {
                return 1;
            }
        } else {
            throw new ServiceException("用户名已存在");
        }
    }


    @Transactional(rollbackFor = {Exception.class})
    @Override
    public int userOssUpdate(UserOssInfoModel userOssInfoModel) {

        String accessId = userOssInfoModel.getAccessId();

        String accessKey = userOssInfoModel.getAccessKey();

        String dir = userOssInfoModel.getDir();

        String bucket = userOssInfoModel.getBucket();

        String endpoint = userOssInfoModel.getEndpoint();

        int loginId = StpUtil.getLoginIdAsInt();

        TUser user = new TUser();
        user.setAccessId(accessId)
                .setAccessKey(accessKey)
                .setDir(dir)
                .setBucket(bucket)
                .setEndpoint(endpoint);

        int result = tUserDao.update(user, new UpdateWrapper<TUser>().eq("id", loginId));

        if (result != 0) {
            return 1;
        } else {
            throw new ServiceException("用户Oss信息数据库更新失败，请联系管理员！");
        }


    }

    @Override
    public int passwordUpdate(PasswordUpdateModel passwordUpdateModel) {

        //验证二次密码是否正确
        String accountPassword = passwordUpdateModel.getAccountPassword();

        String newAccountPassword = passwordUpdateModel.getNewAccountPassword();

        String newAccountPasswordAgain = passwordUpdateModel.getNewAccountPasswordAgain();


        if (!newAccountPassword.equals(newAccountPasswordAgain)) {
            throw new ServiceException("用户二次密码不正确！");
        }

        //验证用户密码是否正确
        int userId = StpUtil.getLoginIdAsInt();
        TUser user = tUserDao.selectById(userId);

        if (!Objects.equals(DigestUtil.md5Hex(accountPassword), user.getAccountPassword())){
            throw new ServiceException("用户密码不正确！");
        }
        String newPassword = DigestUtil.md5Hex(newAccountPassword);
        user.setAccountPassword(newPassword);

        //修改密码
        int i = tUserDao.updateById(user);

        //判断结果
        if (i != 0) {
            return 1;
        } else {
            throw new ServiceException("用户密码数据库更新失败，请联系管理员！");
        }
    }
}
