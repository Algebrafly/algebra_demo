package com.algebra.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author al
 * @date 2022/2/14 17:45
 * @description
 */
@Component
@Slf4j
public class CountTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<CountTimeGatewayFilterFactory.Config> {

    private static final String COUNT_START_TIME = "countStartTime";

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {
            if (!config.isEnabled()) {
                return chain.filter(exchange);
            }
            exchange.getAttributes().put(COUNT_START_TIME, System.currentTimeMillis());
            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> {
                        Long startTime = exchange.getAttribute(COUNT_START_TIME);
                        if (startTime != null) {
                            StringBuilder sb = new StringBuilder(exchange.getRequest().getURI().getRawPath())
                                    .append(": ")
                                    .append(System.currentTimeMillis() - startTime)
                                    .append("ms");
                            sb.append(" params:").append(exchange.getRequest().getQueryParams());
                            log.info(sb.toString());
                        }
                    })
            );
        };
    }

    public static class Config {
        /**
         * 控制是否开启统计
         */
        private boolean enabled;

        public Config() {}

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}
