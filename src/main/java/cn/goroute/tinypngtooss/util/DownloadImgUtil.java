package cn.goroute.tinypngtooss.util;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.FileNameUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author Alickx
 * @Date 2021/10/24 9:06
 */
public class DownloadImgUtil {

    /**
     * 下载压缩后的图片，并保存在temp_image
     */
    public static String downloadImageFile(String url, String fileName) {

        //获取文件的后缀名
        String prefix = FileNameUtil.extName(fileName);

        //获取文件的基础名
        String fileBaseName = FileNameUtil.mainName(fileName);


        try {
            URL tinyPng = new URL(url);

            URLConnection connection = tinyPng.openConnection();

            InputStream is = connection.getInputStream();

            byte[] bytes = new byte[1024];

            int len;

            String projectPath = System.getProperty("user.dir");

            String filePath = projectPath+ "\\src\\main\\resources\\temp_image\\" + fileBaseName + "." + prefix;

            File file = new File(filePath);

            FileOutputStream os = new FileOutputStream(file, true);

            while ((len = is.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
            // 完毕，关闭所有链接
            IoUtil.close(is);
            IoUtil.close(os);


            return filePath;


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

}
