package cn.goroute.tinypngtooss.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.goroute.tinypngtooss.dao.TUserDao;
import cn.goroute.tinypngtooss.exception.ServiceException;
import cn.goroute.tinypngtooss.pojo.TUser;
import cn.goroute.tinypngtooss.pojo.UploadFile;
import cn.goroute.tinypngtooss.service.ImageService;
import cn.goroute.tinypngtooss.util.ossupload.DownloadImgUtil;
import cn.goroute.tinypngtooss.util.ossupload.OssClientUtil;
import cn.goroute.tinypngtooss.util.ossupload.UploadImgToTinyUtil;
import cn.goroute.tinypngtooss.util.ossupload.ValidationImgUtil;
import cn.goroute.tinypngtooss.util.resresult.RespResult;
import cn.goroute.tinypngtooss.util.resresult.Result;
import cn.hutool.core.util.IdUtil;
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
    TUserDao userDao;


    /**
     * 调用方法获取压缩上传oss后的外链
     *
     * @param imageFile 图片文件
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Result uploadImage(MultipartFile[] imageFile) {

        //获取userId
        int userId = StpUtil.getLoginIdAsInt();


        //对图片文件进行校验
        boolean result = ValidationImgUtil.validationImg(imageFile);

        List<UploadFile> urlList = new ArrayList<>();

        if (result) {
            //调用方法获取tinypng压缩后的文件外链
            try {
                for (MultipartFile multipartFile : imageFile) {
                    String tinyPngImgUrl = UploadImgToTinyUtil.uploadImageFileToTinyPng(multipartFile);
                    //根据压缩后的链接下载图片,返回下载回来的路径
                    if (tinyPngImgUrl != null) {

                        //随机文件名
                        String randomFileName = IdUtil.fastSimpleUUID();

                        String downloadFilePath = DownloadImgUtil.downloadImageFile(tinyPngImgUrl,
                                randomFileName);

                        if (downloadFilePath != null) {
                            //获取用户信息
                            TUser user = userDao.selectById(userId);

                            if (user != null) {

                                //把下载的图片文件上传到oss,返回文件外链

                                String ossImgUrl = OssClientUtil.uploadImageToOss(downloadFilePath,
                                        multipartFile.getOriginalFilename(), user);

                                //返回Oss的图片外链
                                urlList.add(new UploadFile(multipartFile.getOriginalFilename(),ossImgUrl));
                            }
                        } else {
                            throw new ServiceException("下载图片文件失败！");
                        }
                    } else {
                        throw new ServiceException("tinyPNG压缩出错！");
                    }
                }
                return RespResult.success(urlList);
            } catch (IOException e) {
                throw new ServiceException(e.getMessage());
            }
        }
        throw new ServiceException("文件不能为空！");
    }
}
