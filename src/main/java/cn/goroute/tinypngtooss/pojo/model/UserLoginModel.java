package cn.goroute.tinypngtooss.pojo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author Alickx
 * @Date 2021/10/25 23:20
 * 用户登陆的模型类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginModel implements Serializable {


    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String accountName;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
    private String accountPassword;


    /**
     * 是否记住我
     */
    private String rememberMe;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String verificationCode;

    private static final long serialVersionUID = 1L;

}
