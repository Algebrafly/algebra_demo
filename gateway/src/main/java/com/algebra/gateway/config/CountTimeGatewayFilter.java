package com.algebra.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author al
 * @date 2022/2/14 17:00
 * @description
 */
//@Component
@Slf4j
public class CountTimeGatewayFilter implements GlobalFilter, Ordered {

    private static final String COUNT_START_TIME = "countStartTime";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String rawPath = exchange.getRequest().getURI().getRawPath();
        Map<String, Object> attributes = exchange.getAttributes();
        long startMilli = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8)).toEpochMilli();
        attributes.put(COUNT_START_TIME, startMilli);
        Date start = new Date();
        start.setTime(startMilli);
        log.info(rawPath+": "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(start));
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    long startTime = exchange.getAttribute(COUNT_START_TIME);
                    long endMilli = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8)).toEpochMilli();
                    Date end = new Date();
                    end.setTime(endMilli);
                    log.info(rawPath+": "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(end));
                    log.info(rawPath + ": " + (endMilli - startTime) + "ms");
                })
        );
    }

    @Override
    public int getOrder() {
        return 9;
    }
}
