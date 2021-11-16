package cn.goroute.tinypngtooss.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.goroute.tinypngtooss.service.ImageService;
import cn.goroute.tinypngtooss.util.resresult.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Alickx
 * @Date 2021/10/23 11:07
 * 获取浏览器上传的图片
 */
@RestController
@Slf4j
@RequestMapping("/api")
public class ImageController {

    @Autowired
    ImageService imageService;

    @SaCheckLogin
    @PostMapping("/image/uploadImage")
    public Result uploadImage(@RequestParam(value = "file") MultipartFile[] imageFile) {
        return imageService.uploadImage(imageFile);

    }

}
