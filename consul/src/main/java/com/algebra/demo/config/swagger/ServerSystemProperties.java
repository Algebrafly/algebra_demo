package com.algebra.demo.config.swagger;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author admin
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:demo-swagger.properties"})
@ConfigurationProperties(prefix = "demo.system")
public class ServerSystemProperties {
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
    private SwaggerProperties swagger = new SwaggerProperties();
}
