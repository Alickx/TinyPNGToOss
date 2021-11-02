package cn.goroute.tinypngtooss.service.impl;

import cn.goroute.tinypngtooss.dao.TUserDao;
import cn.goroute.tinypngtooss.exception.ServiceException;
import cn.goroute.tinypngtooss.pojo.TUser;
import cn.goroute.tinypngtooss.service.ImageService;
import cn.goroute.tinypngtooss.util.DownloadImgUtil;
import cn.goroute.tinypngtooss.util.OssClientUtil;
import cn.goroute.tinypngtooss.util.UploadImgToTinyUtil;
import cn.goroute.tinypngtooss.util.ValidationImgUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author Alickx
 * @Date 2021/10/23 12:55
 * 上传图片
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Resource
    TUserDao UserDao;


    /**
     * 调用方法获取压缩上传oss后的外链
     *
     * @param imageFile 图片文件
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public List<String> uploadImage(MultipartFile[] imageFile, int userId) {

        //对图片文件进行校验
        boolean result = ValidationImgUtil.validationImg(imageFile);

        List<String> urlList = new ArrayList<>();

        if (result) {
            //调用方法获取tinypng压缩后的文件外链
            try {
                for (MultipartFile multipartFile : imageFile) {
                    String tinyPngImgUrl = UploadImgToTinyUtil.uploadImageFileToTinyPng(multipartFile);
                    //根据压缩后的链接下载图片,返回下载回来的路径
                    if (tinyPngImgUrl != null) {
                        String downloadFilePath = DownloadImgUtil.downloadImageFile(tinyPngImgUrl, multipartFile.getOriginalFilename());

                        if (downloadFilePath != null) {
                            //获取用户信息
                            TUser user = UserDao.selectById(userId);

                            if (user != null) {

                                //把下载的图片文件上传到oss,返回文件外链

                                String ossImgUrl = OssClientUtil.uploadImageToOss(downloadFilePath, multipartFile.getOriginalFilename(), user);

                                //返回Oss的图片外链
                                urlList.add(ossImgUrl);
                            }
                        }
                    }
                }
                return urlList;
            } catch (IOException e) {
                throw new ServiceException(e.getMessage());
            }
        }
        throw new ServiceException("文件不能为空！");
    }
}
