package com.algebra.demo.auth;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @apiNote data processing
 * @since 2021/2/23 14:12
 * @author al
 * @version v1.0.0
 */
public class DataFilterHandler {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static boolean isSeeAll(int dataType) {
        return dataType==1;
    }

    public static boolean notHasFilterData(String custstoreFilterData, String carFilterData) {
        if (custstoreFilterData==null&&carFilterData==null) {
            return true;
        }
        return false;
    }

    /**
     * 查询
     * @param userId
     * @return
     */
    public int getDataTypeByUserId(String userId) {
        //get role by userid
        QueryHandler query = new QueryHandler(jdbcTemplate);
        List<String> dataTypeList = query.selectDataTypeByUserId(userId);
        // if role dataType is null, no premission see data
        if (CollectionUtils.isEmpty(dataTypeList)) {
            return 0;
        }
        //卫语句
        //see all
        if (dataTypeList.contains("1")) {
            return 1;
        }
        //see own organ
        if (dataTypeList.contains("2")) {
            return 2;
        }
        //see own organ and underling
        if (dataTypeList.contains("3")) {
            return 2;
        }
        //see equipment
        if (dataTypeList.contains("4")) {
            return 4;
        }
        return 0;
    }

    /**
     * 直接使用jdbc查询用户角色和机构信息
     * @return
     */
    public String getCuststoreFilterDataByUserId(String userId, int dataType) {
        QueryHandler query = new QueryHandler(jdbcTemplate);
        switch (dataType) {
            case 1:
                return null;
            case 2:
            case 3:
                List<String> allCuststoreIds = null ;
                try {
                    allCuststoreIds = query.selectAllCuststoreIdByUserId(userId);
                } catch (Exception e) {
                    e.printStackTrace();
                    // TODO 测试使用
                    allCuststoreIds = new ArrayList<>();
                    allCuststoreIds.add("1");
                }
                if (CollectionUtils.isEmpty(allCuststoreIds)) {
                    return null;
                }
                return JSON.toJSONString(allCuststoreIds);
            case 4:
                List<String> pickCuststoreIds = query.selectPickCuststoreIdByUserId(userId);
                if (CollectionUtils.isEmpty(pickCuststoreIds)) {
                    return null;
                }
                return JSON.toJSONString(pickCuststoreIds);
            default:
                // if role dataType is null, no premission see data
                return JSON.toJSONString(Arrays.asList("-1"));
        }

    }

    /**
     * 直接使用jdbc查询用户绑定车辆信息
     * @return
     */
    public String getCarFilterDataByUserId(String userId, int dataType) {
        QueryHandler query = new QueryHandler(jdbcTemplate);
        switch (dataType) {
            case 1:
            case 2:
            case 3:
                return null;
            case 4:
                List<String> deviceIds = query.selectDeviceIdByUserId(userId);
                if (CollectionUtils.isEmpty(deviceIds)) {
                    return null;
                }
                return JSON.toJSONString(deviceIds);
            default:
                // if role dataType is null, no premission see data
                return JSON.toJSONString(Arrays.asList("-1"));
        }

    }

}
