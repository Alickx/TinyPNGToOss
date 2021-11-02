package cn.goroute.tinypngtooss.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * history_image_user
 * @author 
 */
@Data
public class HistoryImageUser implements Serializable {
    /**
     * 默认id
     */
    private Integer id;

    private Integer userId;

    /**
     * 图片名字
     */
    private String imageName;

    private String imageLink;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}