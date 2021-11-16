package cn.goroute.tinypngtooss.util.encryption;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;

/**
 * @Author Alickx
 * @Date 2021/11/16 8:47
 */
public class MyEncryption {

    public static String EncryptKey = "5f8hkdytc57swe5c";

    public static String salt = "0Co5d8rgyw8W8jud";


    public static String encryptHex(String content) {

        // 构建
        AES aes = new AES(Mode.CTS, Padding.PKCS5Padding,EncryptKey.getBytes(),salt.getBytes());

        // 加密为16进制表示
        return aes.encryptHex(content);
    }

    public static String decryptStr(String encryptHex) {

        // 构建
        AES aes = new AES(Mode.CTS, Padding.PKCS5Padding,EncryptKey.getBytes(),salt.getBytes());

        return aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
    }

}
