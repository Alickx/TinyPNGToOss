package cn.goroute.tinypngtooss.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author Alickx
 * @Date 2021/10/26 9:42
 * 验证码api
 */

@Controller
@RequestMapping("/api")
public class VerificationController {

    /**
     * 获取验证码
     */
    @RequestMapping("/getVerificationCode")
    public void createVerificationCode(HttpServletResponse response, HttpServletRequest request){

        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100,4,10);

        //设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");

        //设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");

        try {

            //获取验证码
            String verificationCode = captcha.getCode();

            //放置Session域对象中
            HttpSession session = request.getSession();
            session.setAttribute("verificationCode",verificationCode);
            captcha.write(response.getOutputStream());
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
