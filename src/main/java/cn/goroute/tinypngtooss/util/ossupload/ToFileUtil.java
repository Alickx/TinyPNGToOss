package cn.goroute.tinypngtooss.util.ossupload;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

/**
 * @ClassName MultipartFileToFile
 * @Description MultipartFile转fie
 **/
public class ToFileUtil {

    /**
     * MultipartFile 转 File
     */
    public static File multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        InputStream ins = file.getInputStream();
        inputStreamToFile(ins, toFile);
        ins.close();
        return toFile;
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = ins.read(buffer, 0, 1024)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}