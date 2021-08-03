package com.algebra.authentication.socket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author al
 * @date 2021/1/12 8:59
 * @description
 */
//@Configuration
public class WebSocketConfig {

//    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
