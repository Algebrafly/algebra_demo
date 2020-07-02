package com.algebra.authentication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.wf.jwtp.TokenInterceptor;

/**
 * @author al
 * @date 2019/12/6 14:34
 * @description
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String[] EXCLUDE_PATH_PATTERNS = {"/swagger-resources/**", "/webjars/**"
            , "/v2/**", "/swagger-ui.html/**"
            , "/swagger-ui.html#!/**"
            ,"/csrf", "/error","/","/login"};

    @Autowired
    MyTokenInterceptor tokenInterceptor;

//    org.wf.jwtp.TokenInterceptor

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**").excludePathPatterns(EXCLUDE_PATH_PATTERNS);
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
