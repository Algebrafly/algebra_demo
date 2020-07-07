package com.algebra.authentication.config;

import com.algebra.authentication.vo.UserInfoDto;
import com.algebra.authentication.util.PassToken;
import com.algebra.authentication.util.UserLoginToken;
import com.algebra.authentication.util.WebApiResult;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

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
public class  MyTokenInterceptor extends HandlerInterceptorAdapter {


    @Autowired
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");
        log.info("[preHandle]接受到token：{}", token);
//        if(token != null){
//            String s = token.substring(7,27);
//            log.info(s);
//            Decoder<String, byte[]> base64url = Decoders.BASE64URL;
//            byte[] bytes = (byte[])base64url.decode(s);
//            String payload = new String(bytes, Strings.UTF_8);
//            log.info(payload);
//        }

        if (!(handler instanceof HandlerMethod)) {
            // 如果不是映射到方法直接通过
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //检查是否有passToken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            return passToken.required();
        }

        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                if(token == null){
                    log.error("[preHandle] Token 不存在！");
                    this.responseRst(response, WebApiResult.error("token不存在！"));
                    return false;
                }

                String userId = "";
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException e) {
                    log.error("JWT解析token异常，异常信息：{}", e.getMessage());
                    this.responseRst(response, WebApiResult.error(e));
                    return false;
                }

                // 查询用户信息
//                UserInfoVo userInfo = userService.getUserByPrimaryKey(userId);
                UserInfoDto userInfoDto = new UserInfoDto();

                if(userInfoDto == null){
                    this.responseRst(response, WebApiResult.error("用户不存在，请重新登陆！"));
                    return false;
                }

                // 验证token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userInfoDto.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    log.error("JWT解析token异常，异常信息：{}", e.getMessage());
                    this.responseRst(response, WebApiResult.error(e));
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

}
