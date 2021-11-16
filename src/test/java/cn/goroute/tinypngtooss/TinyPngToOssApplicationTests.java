package cn.goroute.tinypngtooss;

import cn.goroute.tinypngtooss.dao.TUserDao;
import cn.goroute.tinypngtooss.util.encryption.MyEncryption;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${Tencent.SecretId}")
    private String id;

    @Test
    public void getProperties() {

        System.out.println(id);

    }


    @Test
    public void getEncryptHex(){

        String encryptHex = MyEncryption.encryptHex("bvykFTP5UdU5QUHn21jGWVDepkOlrgEV");


        System.out.println(encryptHex);

    }

    @Test
    public void decryptStr(){
        String str = MyEncryption.decryptStr("c410dd7099963881640d6b4343c861bce5d3b989f1e6b9119b770e18f69be2d0");

        System.out.println(str);
    }


}
