package com.algebra.authentication.config;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONUtil;
import com.algebra.authentication.domain.SysUser;
import com.algebra.authentication.service.config.OauthTokenService;
import com.algebra.authentication.service.rbac.SysUserService;
import com.algebra.authentication.util.MdcConstant;
import com.algebra.authentication.util.UserLoginToken;
import com.algebra.authentication.util.WebApiResult;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.wf.jwtp.annotation.Ignore;
import org.wf.jwtp.util.TokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

/**
 * @author al
 * @date 2020/6/22 9:28
 * @description
 */
@Component
@Slf4j
public class LogHeaderInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String requestStr = null;
        if(HttpMethod.GET.name().equals(method)){
            requestStr = request.getQueryString();
        } else if(HttpMethod.POST.name().equals(method)){
            requestStr = IoUtil.read(request.getReader());
        } else {
            requestStr = "";
        }
        log.info("[LOG]-[Method={}，URI = {}] 请求参数：{}", method, uri, requestStr);

        String timeZone = getTimeZone(request);


        return true;
    }

    private void responseRst(HttpServletResponse response, WebApiResult<String> result) throws IOException {
        HttpOutputMessage httpOutputMessage = new ServletServerHttpResponse(response);
        mappingJackson2HttpMessageConverter.write(result,
                MediaType.APPLICATION_JSON, httpOutputMessage);
    }

    private String getTimeZone(HttpServletRequest request) {
        String timeZone = request.getParameter("timeZone");
        if (timeZone == null || timeZone.trim().isEmpty()) {
            timeZone = request.getHeader("timeZone");
        }
        return timeZone;
    }

}
