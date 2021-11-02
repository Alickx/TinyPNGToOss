package cn.goroute.tinypngtooss.config;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.thymeleaf.dialect.SaTokenDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

/**
 * @Author Alickx
 * @Date 2021/10/30 23:52
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaAnnotationInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**","/js/**","/img/**");
    }

    @Bean
    public SaTokenDialect getSaTokenDialect() {
        return new SaTokenDialect();
    }

    @Autowired
    private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        viewResolver.addStaticVariable("stp", StpUtil.stpLogic);
    }
}
