package cn.goroute.tinypngtooss.pojo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author Alickx
 * @Date 2021/10/26 9:16
 * 用户注册模型类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @NotBlank(message = "账号不能为空")
    @Length(min = 6,max = 14,message = "账号长度应该为6~14位")
    private String accountName;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 8,max = 16,message = "密码长度应该为8~16位")
    private String accountPassword;

    /**
     * 再次确认密码
     */
    @NotBlank(message = "二次密码不能为空")
    @Length(min = 8,max = 16,message = "密码长度应该为8~16位")
    private String accountPasswordAgain;


    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String verificationCode;


}
