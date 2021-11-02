package cn.goroute.tinypngtooss.util;

import cn.goroute.tinypngtooss.exception.ServiceException;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author Alickx
 * @Date 2021/10/24 9:01
 * 上传图片到tinypng上
 * 返回图片的url值
 */
public class UploadImgToTinyUtil {

    /**
     * 上传图片文件到tinypng
     */
    public static String uploadImageFileToTinyPng(MultipartFile imageFile) throws IOException {


        try {
            File file = ToFileUtil.multipartFileToFile(imageFile);

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, file);
            Request request = new Request.Builder()
                    .url("https://tinypng.com/web/shrink")
                    .method("POST", body)
                    .addHeader("Cache-Control", "no-cache")
                    .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36 Edg/95.0.1020.30")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            Response response = client.newCall(request).execute();

            ToFileUtil.delteTempFile(file);

            JSONObject respJson = JSONObject.parseObject(Objects.requireNonNull(response.body()).string());
            JSONObject output = JSONObject.parseObject(respJson.getString("output"));

            //获得Url，添加进数组
            String url = output.getString("url");

            return url;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }


}
