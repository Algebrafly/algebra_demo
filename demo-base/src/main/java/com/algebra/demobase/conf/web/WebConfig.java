package com.algebra.demobase.conf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author al
 * @date 2019/12/6 14:34
 * @description
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String[] excludePathPatterns  = {"/api/token/user_token"};

    @Autowired
    private TokenInterceptor tokenInterceptor;
    @Autowired
    private LimitRefreshInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/api/**").excludePathPatterns(excludePathPatterns);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
                .allowCredentials(false).maxAge(3600);
    }
}
