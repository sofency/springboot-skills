package com.example.templateutils.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>Project: springboot-skills - WebMvcConfig
 * <p>Powered by echo On 2024-08-22 10:58:05
 *
 * @author sofency [sofency@qq.com]
 * @version 1.0
 * @since 17
 * 前后端分离项目设置允许跨域
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //对所有路径生效
                .allowedOrigins("*") // 允许所有源地址 可以指定
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*"); // 允许的请求头
    }
}
