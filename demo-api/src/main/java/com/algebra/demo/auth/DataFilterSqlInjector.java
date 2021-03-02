package com.algebra.demo.auth;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.MDC;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * @apiNote sql拦截实现权限注入
 * @since 2021/2/22 8:37
 * @author al
 * @version v1.0.0
 */
@Accessors(chain = true)
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
@ConfigurationProperties(prefix = "frc.authority")
@Slf4j
@Data
public class DataFilterSqlInjector extends AbstractSqlParserHandler implements Interceptor {

    private boolean dataFilterEnable = false;

    /**
     * copy with {@link com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor}
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // enable filter?
        if (!dataFilterEnable) {
            return invocation.proceed();
        }
        // is allocate?
        String sqlFilterEnable = MDC.get(DataFilterUtils.SQL_FILTER_ENABLE);
        if (!Boolean.parseBoolean(sqlFilterEnable)) {
            return invocation.proceed();
        }
        log.info("[行数据权限] 进入sql拦截器");
        // find really method
        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);

        // SQL 解析
        this.sqlParser(metaObject);

        // 先判断是不是SELECT操作  (2019-04-10 00:37:31 跳过存储过程)
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        if (SqlCommandType.SELECT != mappedStatement.getSqlCommandType()
                || StatementType.CALLABLE == mappedStatement.getStatementType()) {
            return invocation.proceed();
        }

        // 针对定义了rowBounds，做为mapper接口方法的参数
        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        String buildSql = buildSql(boundSql);

        List<ParameterMapping> mappings = new ArrayList<>(boundSql.getParameterMappings());
        metaObject.setValue("delegate.boundSql.sql", buildSql);
        metaObject.setValue("delegate.boundSql.parameterMappings", mappings);

        log.info("[行数据权限] 离开sql拦截器 sql：{}",buildSql);
        return invocation.proceed();
    }

    private String buildSql(BoundSql boundSql) {
        String custstoreidAlias = MDC.get(DataFilterUtils.SQL_ALIAS_CUSTSTOREID);
        String equipmentAlias = MDC.get(DataFilterUtils.SQL_ALIAS_EQUIPMENT);
        String custstoreidData = MDC.get(DataFilterUtils.SQL_FILTER_DATA_CUSTSTOREID);
        String equipmentData = MDC.get(DataFilterUtils.SQL_FILTER_DATA_EQUIPMENT);
        SqlHandler sql = new SqlHandler(boundSql);
        String originalSql = sql.getSql();
        //custstore sql building
        String buildSql = sql.concatAuthSql(originalSql,custstoreidAlias,custstoreidData);
        //custstore sql building
        buildSql = sql.concatAuthSql(buildSql,equipmentAlias,equipmentData);
        return buildSql;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor || target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
