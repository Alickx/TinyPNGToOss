package cn.goroute.tinypngtooss.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * t_user
 * @author 
 */
@Data
@Accessors(chain = true)
public class TUser implements Serializable {
    /**
     * 默认id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名称
     */
    private String accountName;

    /**
     * 用户密码
     */
    private String accountPassword;


    /**
     * 传输oss的参数accessId
     */
    private String accessId;

    /**
     * 传输oss的参数accessKey
     */
    private String accessKey;

    /**
     * 传输oss的参数endpoint
     */
    private String endpoint;

    /**
     * 传输oss的参数bucket
     */
    private String bucket;

    /**
     * 传输oss的参数dir，图片的保存路径
     */
    private String dir;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}