package cn.goroute.tinypngtooss.util.ossupload;


import cn.goroute.tinypngtooss.exception.ServiceException;
import cn.goroute.tinypngtooss.pojo.TUser;
import cn.hutool.core.io.FileUtil;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;

import java.io.File;


/**
 * @Author Alickx
 * @Date 2021/10/23 17:03
 */
public class OssClientUtil {

    public static String uploadImageToOss(String filePath,String fileName, TUser user) {

        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = user.getEndpoint();
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = user.getAccessId();
        String accessKeySecret = user.getAccessKey();
        String dir = user.getDir();
        String bucketName = user.getBucket();


        File file = new File(filePath);

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建PutObjectRequest对象。
        // 依次填写Bucket名称（例如examplebucket）、Object完整路径（例如exampledir/exampleobject.txt）和本地文件的完整路径。Object完整路径中不能包含Bucket名称。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, dir+"/"+fileName, file);

        // 上传文件。
        try {
            ossClient.putObject(putObjectRequest);
        } catch (OSSException | ClientException e) {
            FileUtil.del(filePath);
            throw new ServiceException("Oss信息填写错误");
        }


        // 关闭OSSClient。
        ossClient.shutdown();


        FileUtil.del(filePath);

        return "https://"+bucketName+"."+endpoint+"/"+dir+"/"+fileName;
    }

}
