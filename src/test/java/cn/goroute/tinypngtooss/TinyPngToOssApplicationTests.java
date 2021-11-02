package cn.goroute.tinypngtooss;

import cn.goroute.tinypngtooss.dao.TUserDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class TinyPngToOssApplicationTests {

    @Resource
    TUserDao tUserDao;

    @Test
    void contextLoads() {

        String str = "1111.png";

        System.out.println(str.substring(str.lastIndexOf(".")));

    }


    @Test
    void getDir() {

        String PROJECT_PATH = System.getProperty("user.dir");//项目在硬盘上的基础路径
        System.out.println(PROJECT_PATH);
        System.out.println(PROJECT_PATH + "\\src\\main\\resources\\temp_image");

    }


}
