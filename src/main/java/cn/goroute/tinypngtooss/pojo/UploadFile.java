package cn.goroute.tinypngtooss.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author Alickx
 * @Date 2021/11/10 21:41
 */
@Data
@AllArgsConstructor
public class UploadFile {

    /**
     * 上传文件的名字
     */
    private String fileName;

    /**
     * 上传文件的链接
     */
    private String url;


}
