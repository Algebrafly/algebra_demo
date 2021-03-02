package com.algebra.demo.auth;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.validation.constraints.NotNull;
import java.util.List;

public class QueryHandler {

    private JdbcTemplate jdbcTemplate;

    protected QueryHandler(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @NotNull
    public List<String> selectDataTypeByUserId(String userId) {
        String sql = "SELECT DISTINCT sys_role.datatype FROM sys_user_role " +
                "LEFT JOIN sys_role ON sys_role.roleid = sys_user_role.roleid " +
                "WHERE sys_user_role.userid = ? and sys_user_role.deleted='f'";
        List<String> dataTypeList = jdbcTemplate.queryForList(sql, String.class, userId);
        return dataTypeList;
    }

    @NotNull
    public List<String> selectAllCuststoreIdByUserId(String userId) {
        String sql = "select DISTINCT custstoreid from (" +
                "select custstoreid from sys_user_custstore c where c.userid=? and c.deleted='f' " +
                "union all " +
                "select permission.departmentid from sys_roledatapermission permission " +
                "left join sys_role r on r.roleid=permission.roleid and r.deleted='f' " +
                "left join sys_user_role ur on r.roleid=ur.roleid and ur.deleted='f' " +
                "where ur.userid= ? and permission.deleted='f' " +
                ") a";
        List<String> custstoreIds = jdbcTemplate.queryForList(sql, String.class, userId,userId);
        return custstoreIds;
    }

    @NotNull
    public List<String> selectPickCuststoreIdByUserId(String userId) {
        String sql = "select distinct permission.departmentid from sys_roledatapermission permission " +
                "left join sys_role r on r.roleid=permission.roleid and r.deleted='f' " +
                "left join sys_user_role ur on r.roleid=ur.roleid and ur.deleted='f' " +
                "where ur.userid= ? and permission.deleted='f'";
        List<String> custstoreIds = jdbcTemplate.queryForList(sql, String.class, userId);
        return custstoreIds;
    }

    public List<String> selectDeviceIdByUserId(String userId) {
        String sql = "select DISTINCT deviceid from sys_user_device where userid=? and deleted='f'";
        List<String> deviceIds = jdbcTemplate.queryForList(sql, String.class, userId);
        return deviceIds;
    }
}
