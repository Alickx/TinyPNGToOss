package cn.goroute.tinypngtooss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Alickx
 */
@SpringBootApplication
@MapperScan(value = "cn.goroute.tinypngtooss.dao")
public class TinyPngToOssApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinyPngToOssApplication.class, args);
    }

}
