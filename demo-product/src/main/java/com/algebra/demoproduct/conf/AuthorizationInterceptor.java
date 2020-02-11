package com.algebra.demoproduct.conf;

import com.algebra.demo.annotation.Annoymous;
import com.algebra.demo.annotation.Login;
import com.algebra.demo.util.trace.WebTraceUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author al
 * @date 2020/2/11 9:42
 * @description 拦截前端请求，解析并验证token数据
 */
@Component
@Slf4j
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean canSkip = true;
        // 免token校验的接口
        if (handler instanceof HandlerMethod) {
            String beanTypeName = ((HandlerMethod) handler).getBeanType().getName();
            log.info("BeanTypeName:{}", beanTypeName);
            String methodName = ((HandlerMethod) handler).getMethod().getName();
            log.info("methodName:{}", methodName);
            if (((HandlerMethod) handler).hasMethodAnnotation(Login.class)) {
                canSkip = true;
            } else {
                if (((HandlerMethod) handler).hasMethodAnnotation(Annoymous.class)) {
                    canSkip = true;
                }
            }
        }
        // 解析token
        String token = request.getHeader("token");
        log.info("[web请求校验] 接受到的token值为：{}", token);
        if(token != null){
            // 请求校验token服务
            // ...

            setTraceParamByToken(request);
            log.info("[web请求校验]token校验成功！");
            canSkip = true;
        }

        return canSkip;
    }

    /**
     * spring-mvc: 拦截器处理自定义MDC链路参数
     * @param request 请求
     */
    private void setTraceParamByToken(HttpServletRequest request){
        String clientId = request.getHeader(WebTraceUtil.CLIENT_ID);

        //如果当前clientId为空或者为默认clientId，则生成新的clientId
        if (StringUtils.isBlank(clientId) || WebTraceUtil.defaultClientId(clientId)){
            clientId = WebTraceUtil.initClientId();
        }
        //设置clientId
        WebTraceUtil.setClientId(clientId);
    }
}
