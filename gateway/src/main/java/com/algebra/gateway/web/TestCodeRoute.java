package com.algebra.gateway.web;

import com.algebra.gateway.config.CountTimeGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author al
 * @date 2022/2/14 17:16
 * @description
 */
@Component
public class TestCodeRoute {

//    @Bean
//    RouteLocator routeLocator(RouteLocatorBuilder builder){
//        return builder.routes()
//                .route("testTimeCount",
//                        j -> j.path("/consumer3/**")
//                                .filters(f -> f.filter(new CountTimeGatewayFilter()))
//                                .uri("lb://sys-test")
//                ).build();
//    }

}
