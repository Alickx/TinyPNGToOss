package cn.goroute.tinypngtooss.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.goroute.tinypngtooss.service.ImageService;
import cn.goroute.tinypngtooss.util.resresult.RespResult;
import cn.goroute.tinypngtooss.util.resresult.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author Alickx
 * @Date 2021/10/23 11:07
 * 获取浏览器上传的图片
 */
@Controller
@Slf4j
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageService imageService;

    @SaCheckLogin
    @PostMapping("/uploadImage")
    @ResponseBody
    public Result uploadImage(@RequestParam(value = "file") MultipartFile[] imageFile) {

        //获取用户的id
        int userId = StpUtil.getLoginIdAsInt();

        //获取压缩后上传Oss的图片外链
        List<String> urlMap = imageService.uploadImage(imageFile, userId);

        return RespResult.success(urlMap);


    }

}
