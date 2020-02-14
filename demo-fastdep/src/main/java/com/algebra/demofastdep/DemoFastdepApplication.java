package com.algebra.demofastdep;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author al
 * @desc fastdep 实现多数据源配置[mysql/redis/...]
 * @note 项目启动不需要任何其他配置
 *      1.当出现数据库权限错误时候 执行SQL： GRANT XA_RECOVER_ADMIN ON *.* TO root@'%';
 *      2.不需要额外引入 Druid 数据源的依赖包
 *      3.原理：a.[核心] FastDepDataSourceRegister代码实现数据源注册，实现EnvironmentAware接口，从而获取application.yml配置
 *                  文件中数据源的配置信息，实现ImportBeanDefinitionRegistrar，从而注册 FastDepDataSourceRegister.
 *             b.FastDepDataSourceProperties 读取application.yml配置信息
 *             c.FastDepAtomikosTransactionConfigure 实利用atomikos现了事务
 *             d.FastDepDataSourceAutoConfiguration @Import 将FastDepDataSourceRegister 和 FastDepAtomikosTransactionConfigure注册到Springboot
 *             e./META-INF/spring.factories 下配置加载FastDepDataSourceAutoConfiguration
 *
 */
@SpringBootApplication
public class DemoFastdepApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DemoFastdepApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

}
