package com.algebra.gateway.config;

import com.algebra.gateway.entity.AuthTypeProps;
import com.algebra.gateway.entity.BaseConstant;
import com.algebra.gateway.entity.TokenUser;
import com.algebra.gateway.entity.WebApiResult;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.MultiValueMap;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author al
 * @date 2022/1/21 15:24
 * @description Authentication【认证】、Authorization【授权】
 */
@Component
@Slf4j
public class AuthXFilter implements GlobalFilter, Ordered {

    @Autowired
    private AuthTypeProps authTypeProps;

    private final PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        log.info("[Gateway - AuthFilter] 请求地址：{}，请求资源路径：{}", request.getRemoteAddress(), request.getPath());

        boolean canSkip = false;
        log.debug("1.请求方式是OPTIONS的时候 skip");
        String method = request.getMethod().name();
        if (RequestMethod.OPTIONS.name().equals(method)) {
            canSkip = true;
        }

        log.debug("2.免Token校验的URL（配置的白名单+swagger）");
        String requestUrl = request.getPath().toString();
        List<String> ignoreUrlList = authTypeProps.getIgnoreUrlList();
        if (ignoreUrlList != null && ignoreUrlList.size() > 0) {
            canSkip = ignoreUrlList.stream().anyMatch(urlPattern -> pathMatcher.match(urlPattern, requestUrl));
        }
        if (canSkip) {
            return chain.filter(exchange);
        }
        log.debug("3.获取Token，并预先检测是否是配置的GlobalToken");
        String token = this.getToken(request);
        // GlobalToken有两种模式：系统配置、临时认证
        TokenUser globalToken = authTypeProps.getGlobalToken();
        String sysUserToken = globalToken.getSysUser();
        if (sysUserToken.equals(token)) {
            log.info("[系统内置Token]用于请求非用户配置接口");
            return chain.filter(exchange);
        }
        // TODO 临时Token，从缓存中取（key: IP地址），取出后需要CA认证，有效期半小时


        log.debug("4.认证Token，三种方式：user-api、security、jwt（策略+工厂）");


        log.debug("5.用户认证鉴权数据写入缓存");


        log.debug("6.生成TraceId，写入请求头信息");


        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private Mono<Void> responseRst(ServerHttpResponse response) {
        byte[] bits = JSON.toJSONString(WebApiResult.error(401, "Token Invalid")).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    private String getToken(ServerHttpRequest request) {
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        String accessToken = queryParams.getFirst(BaseConstant.TOKEN);
        if (accessToken == null || accessToken.trim().isEmpty()) {
            HttpHeaders headers = request.getHeaders();
            accessToken = headers.getFirst(BaseConstant.TOKEN);
            if (accessToken == null || accessToken.trim().isEmpty()) {
                accessToken = headers.getFirst(BaseConstant.AUTH_TOKEN);
            }
        }
        return accessToken;
    }

}
