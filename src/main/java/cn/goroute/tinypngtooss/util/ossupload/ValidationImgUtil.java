package cn.goroute.tinypngtooss.util.ossupload;

import cn.goroute.tinypngtooss.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Alickx
 * @Date 2021/10/24 9:09
 */
@Slf4j
public class ValidationImgUtil {

    final static Double TINY_PNG_MAX_FILE_SIZE = 5d;

    /**
     * 校验方法
     *
     * @param imageFile 图片文件
     * @return 校验结果 true = 校验通过 false = 校验不通过
     */
    public static boolean validationImg(MultipartFile[] imageFile) {

        /*
          判断文件是否为空，类型是否正确，大小是否不超过限制
         */
        for (MultipartFile multipartFile : imageFile) {
            if (multipartFile.isEmpty()) {
                throw new ServiceException("文件为空！");
            } else if ((double) multipartFile.getSize() / 1048576 > TINY_PNG_MAX_FILE_SIZE) {
                throw new ServiceException("文件大小超过5MB！");
            }
        }
        return true;

    }

}
