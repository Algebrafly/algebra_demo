package com.algebra.demouser.conf;

import brave.Span;
import brave.Tracer;
import com.algebra.demo.util.trace.WebTraceUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author al
 * @date 2020/2/10 16:45
 * @description
 */
@WebFilter(urlPatterns = "/*",filterName = "webTraceFilter")
@Order(1)
@Slf4j
public class WebTraceFilter extends GenericFilterBean {

    private final Tracer tracer;

    WebTraceFilter(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request =  (HttpServletRequest)servletRequest;

        Span currentSpan = this.tracer.currentSpan();
        log.info(currentSpan.toString());
        log.info(currentSpan.context().traceIdString());

        String clientId = initWebTraceParam(request);
        currentSpan.tag("clientId",clientId);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    /**
     * 初始化MDC参数
     * @param request 请求
     */
    private String initWebTraceParam(HttpServletRequest request){
        String clientId = request.getHeader(WebTraceUtil.CLIENT_ID);
        String token = request.getHeader("token");
        log.info("接收到token = {}",token);

        //如果当前clientId为空或者为默认clientId，则生成新的clientId
        if (StringUtils.isBlank(clientId) || WebTraceUtil.defaultClientId(clientId)){
            // 正式环境请使用token请求用户中心服务获取clientId
            // ...
            clientId = WebTraceUtil.initClientId();
        }
        //设置clientId
        WebTraceUtil.setClientId(clientId);
        return clientId;
    }
}
