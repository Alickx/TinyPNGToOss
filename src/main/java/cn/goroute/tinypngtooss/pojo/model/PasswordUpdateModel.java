package cn.goroute.tinypngtooss.pojo.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @Author Alickx
 * @Date 2021/10/30 22:14
 */
@Data
public class PasswordUpdateModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z]\\w{5,17}$",message = "以字母开头，长度在6~18之间，只能包含字母、数字和下划线")
    private String accountPassword;

    /**
     * 新的用户密码
     */
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z]\\w{5,17}$",message = "以字母开头，长度在6~18之间，只能包含字母、数字和下划线")
    private String newAccountPassword;




}
