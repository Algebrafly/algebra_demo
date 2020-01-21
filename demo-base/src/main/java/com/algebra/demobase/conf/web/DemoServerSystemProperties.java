package com.algebra.demobase.conf.web;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author MrBird
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:demo-server-system.properties"})
@ConfigurationProperties(prefix = "demo.server.system")
public class DemoServerSystemProperties {
    /**
     * 免认证 URI，多个值的话以逗号分隔
     */
    private String anonUrl;
    /**
     * 批量插入当批次可插入的最大值
     */
    private Integer batchInsertMaxNum = 1000;
    /**
     * swagger配置
     */
    private DemoSwaggerProperties swagger = new DemoSwaggerProperties();
}
