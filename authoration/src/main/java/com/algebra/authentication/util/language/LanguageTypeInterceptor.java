package com.algebra.authentication.util.language;

import com.algebra.authentication.util.MdcConstant;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author al
 * @date 2020/7/17 15:14
 * @description
 */
@Component
public class LanguageTypeInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String languageName = getLanguageName(request);
        MDC.put(MdcConstant.LANGUAGE_TYPE, LanguageTypeEnum.getTypeByName(languageName));
        return true;
    }

    private String getLanguageName(HttpServletRequest request) {
        String languageType = request.getParameter("lang");
        if (languageType == null || languageType.trim().isEmpty()) {
            languageType = request.getHeader("lang");
            if (languageType == null || languageType.trim().isEmpty()) {
                // 不传默认英语
                languageType = LanguageTypeEnum.ENGLISH.getName();
            }
        }
        return languageType;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Requested-With, token, lang");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Max-Age", "3600");
    }

}
