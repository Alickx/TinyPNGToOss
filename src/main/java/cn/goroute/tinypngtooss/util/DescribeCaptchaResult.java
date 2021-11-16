package cn.goroute.tinypngtooss.util;

import cn.goroute.tinypngtooss.exception.ServiceException;
import com.alibaba.fastjson.JSONObject;
import com.tencentcloudapi.captcha.v20190722.CaptchaClient;
import com.tencentcloudapi.captcha.v20190722.models.DescribeCaptchaResultRequest;
import com.tencentcloudapi.captcha.v20190722.models.DescribeCaptchaResultResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author Alickx
 * @Date 2021/11/7 19:53
 */
@Component
@Slf4j
public class DescribeCaptchaResult {
    @Value("${Tencent.SecretId}")
    private String secretId;

    @Value("${Tencent.SecretKey}")
    private String secretKey;

    @Value("${Tencent.CaptchaAppId}")
    private int captchaAppId;

    @Value("${Tencent.AppSecretKey}")
    private String appSecretKey;

    public JSONObject getTencentCaptchaResult(String ticket, String randstr, String userIp) {
        try {
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
            Credential cred = new Credential(secretId, secretKey);
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("captcha.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            CaptchaClient client = new CaptchaClient(cred, "", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            DescribeCaptchaResultRequest req = new DescribeCaptchaResultRequest();
            req.setCaptchaType(9L);
            req.setTicket(ticket);
            req.setRandstr(randstr);
            req.setUserIp(userIp);
            req.setCaptchaAppId((long) captchaAppId);
            req.setAppSecretKey(appSecretKey);
            // 返回的resp是一个DescribeCaptchaResultResponse的实例，与请求对象对应
            DescribeCaptchaResultResponse resp = client.DescribeCaptchaResult(req);
            // 输出json格式的字符串回包
            return JSONObject.parseObject(DescribeCaptchaResultResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
