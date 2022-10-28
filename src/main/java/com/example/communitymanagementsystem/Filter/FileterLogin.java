package com.example.communitymanagementsystem.Filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * \* @author: Predator
 * \* Date: 2022-10-2
 * \* Time: 9:40
 * \* Description:
 * \拦截器加载到springboot中的配置类
 * 并设置拦截路径
 */
@Configuration
public class FileterLogin implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截除了 /register 和  /login的所有请求
        registry.addInterceptor(new LoginInter())
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/js/**","/css/**","/get/**","/img/**","/register");
    }

}
