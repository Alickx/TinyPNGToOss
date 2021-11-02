package cn.goroute.tinypngtooss.pojo.model;

import lombok.Data;

/**
 * @Author Alickx
 * @Date 2021/10/30 22:14
 */
@Data
public class PasswordUpdateModel {

    /**
     * 用户密码
     */
    private String accountPassword;

    /**
     * 新的用户密码
     */
    private String newAccountPassword;

    /**
     * 新的用户密码二次确认
     */
    private String newAccountPasswordAgain;



}
