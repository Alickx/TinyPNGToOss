package cn.goroute.tinypngtooss.service;

import cn.goroute.tinypngtooss.util.resresult.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Alickx
 * @Date 2021/10/25 21:50
 */

public interface ImageService{

    /**
     * 上传文件
     * @param imageFile 图片文件
     * @return Oss外链
     */
    Result uploadImage(MultipartFile[] imageFile);

}
