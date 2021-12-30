package com.algebra.demo.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis-Plus进行分页
 * * 返回的数据中total总是为0问题
 */
@Configuration
public class MybatisPlusConfigurer {
    /**
     * 分页插件
     *
     * @return PaginationInterceptor
     */


    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
         paginationInterceptor.setLimit(-1);
        // 开启 count 的 join 优化,只针对部分 left join
        return paginationInterceptor;
    }

}
