package com.algebra.demofastdep.conf.db;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author al
 * @date 2020/2/12 14:58
 * @description 数据库slave的配置信息
 */
@Data
@Component
@ConfigurationProperties(prefix = "datasource.slave")
public class SlaveDataBaseProperties {
    String url;
    String username;
    String password;
    String driverClassName;
}
