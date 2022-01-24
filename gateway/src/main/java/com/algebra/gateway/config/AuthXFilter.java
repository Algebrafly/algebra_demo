package com.algebra.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author al
 * @date 2022/1/21 15:24
 * @description Authentication【认证】、Authorization【授权】
 *
 */
public class AuthXFilter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {




        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
