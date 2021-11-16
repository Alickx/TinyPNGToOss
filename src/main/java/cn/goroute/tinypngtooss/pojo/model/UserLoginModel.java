package cn.goroute.tinypngtooss.pojo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$",message = "字母开头，允许5-16字节，允许字母数字下划线")
    private String accountName;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z]\\w{5,17}$",message = "以字母开头，长度在6~18之间，只能包含字母、数字和下划线")
    private String accountPassword;


    /**
     * 验证码参数
     */
    @NotBlank(message = "验证码参数不能为空")
    private String ticket;

    /**
     * 验证码参数
     */
    @NotBlank(message = "验证码参数不能为空")
    private String randstr;

    private static final long serialVersionUID = 1L;

}
