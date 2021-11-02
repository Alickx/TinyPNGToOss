package cn.goroute.tinypngtooss.pojo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Alickx
 * @Date 2021/10/27 9:18
 * 用户更新信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOssInfoModel implements Serializable {

    private static final long serialVersionUID = 1L;


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


}
