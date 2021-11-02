package cn.goroute.tinypngtooss.util;

import cn.goroute.tinypngtooss.exception.ServiceException;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Alickx
 * @Date 2021/10/24 9:09
 */
public class ValidationImgUtil {

    final static Long TINY_PNG_MAX_FILE_SIZE = 40000000L;

    /**
     * 校验方法
     * @return 校验结果 true = 校验通过 false = 校验不通过
     * @param imageFile
     */
    public static boolean validationImg(MultipartFile[] imageFile){


        /*
          判断文件是否为空
         */
        for (MultipartFile multipartFile : imageFile) {
            if (multipartFile.isEmpty()) {
                throw new ServiceException("文件为空！");
            }
        }

        return true;

    }

}
