package com.algebra.demoproduct.conf;

import brave.Span;
import brave.Tracer;
import com.algebra.demo.util.trace.WebTraceUtil;
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
public class WebTraceFilter extends GenericFilterBean {

    private final Tracer tracer;

    WebTraceFilter(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        Span span = this.tracer.currentSpan();

        initWebTraceParam((HttpServletRequest)servletRequest);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    private void initWebTraceParam(HttpServletRequest request){
        String traceId = request.getHeader(WebTraceUtil.TRACE_ID);

        //如果当前traceId为空或者为默认traceId，则生成新的traceId
        if (StringUtils.isBlank(traceId) || WebTraceUtil.defaultTraceId(traceId)){
            traceId = WebTraceUtil.genTraceId();
        }
        //设置traceId
        WebTraceUtil.setTraceId(traceId);
    }
}
