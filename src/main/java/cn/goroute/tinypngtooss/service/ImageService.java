package cn.goroute.tinypngtooss.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author Alickx
 * @Date 2021/10/25 21:50
 */

public interface ImageService{

    /**
     * 上传文件
     * @param imageFile 图片文件
     * @param userId 用户的id
     * @return Oss外链
     */
    List<String> uploadImage(MultipartFile[] imageFile, int userId);

}
