package cn.goroute.tinypngtooss.pojo.vo;

import lombok.Data;

/**
 * @Author Alickx
 * @Date 2021/10/30 17:10
 */
@Data
public class UserVo {

    /**
     * 用户id
     */
    public int id;

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
