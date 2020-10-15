package com.algebra.authentication.config;

import cn.hutool.json.JSONUtil;
import com.algebra.authentication.domain.SysUser;
import com.algebra.authentication.service.config.OauthTokenService;
import com.algebra.authentication.service.rbac.SysUserService;
import com.algebra.authentication.util.MdcConstant;
import com.algebra.authentication.util.UserLoginToken;
import com.algebra.authentication.util.WebApiResult;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author al
 * @date 2020/6/22 9:28
 * @description
 */
@Component
@Slf4j
public class MyTokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    SysUserService userService;

    @Autowired
    OauthTokenService tokenService;

    @Autowired
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();

        String token = this.getToken(request);
        log.info("[preHandle] uri:{}，接受到token：{}", uri, token);

        if (!(handler instanceof HandlerMethod)) {
            // 如果不是映射到方法直接通过
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //检查是否有Ignore注释，有则跳过认证
        if (method.isAnnotationPresent(Ignore.class)) {
            return true;
        }

        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                if(token == null){
                    log.error("[preHandle] Token 不存在！");
                    this.responseRst(response, WebApiResult.error(401, "token无效！"));
                    return false;
                }

                String userId = "";
                try {
                    String tokenKey = tokenService.getTokenKey();
                    log.debug("ACCESS_TOKEN: " + token + "   TOKEN_KEY: " + tokenKey);
                    userId = TokenUtil.parseToken(token, tokenKey);
                } catch (Exception e) {
                    log.error("JWT解析token异常，异常信息：{}", e.getMessage());
                    this.responseRst(response, WebApiResult.error(401, e.getMessage()));
                    return false;
                }

                // 查询用户信息
                SysUser userInfo = userService.getById(userId);
                if(userInfo == null){
                    this.responseRst(response, WebApiResult.error("用户不存在，请检查用户名！"));
                    return false;
                }

                // 验证token
//        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userInfo.getPassword())).build();
                try {
//            jwtVerifier.verify(token);
                    boolean b = tokenService.validTokenByDb(token);
                    if(!b){
                        this.responseRst(response, WebApiResult.error(401, "token已失效，请重新登录！"));
                        return false;
                    }
                    // 携带用户相关信息
                    request.setAttribute("userInfo", JSONUtil.toJsonStr(userInfo));
                    MDC.put(MdcConstant.USER_INFO, JSONUtil.toJsonStr(userInfo));
                    MDC.put(MdcConstant.USER_ID, userId);
                    MDC.put(MdcConstant.TOKEN, token);
                } catch (JWTVerificationException e) {
                    log.error("JWT解析token异常，异常信息：{}", e.getMessage());
                    this.responseRst(response, WebApiResult.error(401, e.getMessage()));
                    return false;
                }
                log.info("[preHandle]Token校验通过！");
                return true;
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Requested-With, token, ClientId");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
    }

    private void responseRst(HttpServletResponse response, WebApiResult<String> result) throws IOException {
        HttpOutputMessage httpOutputMessage = new ServletServerHttpResponse(response);
        mappingJackson2HttpMessageConverter.write(result,
                MediaType.APPLICATION_JSON, httpOutputMessage);
    }

    private String getToken(HttpServletRequest request) {
        String accessToken = request.getParameter("access_token");
        if (accessToken == null || accessToken.trim().isEmpty()) {
            accessToken = request.getHeader("Authorization");
            if (accessToken != null && accessToken.length() >= 7) {
                accessToken = accessToken.substring(7);
            }
        }

        return accessToken;
    }

}
