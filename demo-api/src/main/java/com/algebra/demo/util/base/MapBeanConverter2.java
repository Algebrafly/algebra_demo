package com.algebra.demo.util.base;


import com.algebra.demo.dto.UserDto;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author al
 * @date 2020/2/21 16:38
 * @description
 */
public class MapBeanConverter2 {

    public static void transMap2Bean(Map<String, Object> map, Object obj) {
        if (map == null || obj == null) {
            return;
        }
        try {
            BeanUtils.populate(obj, map);
        } catch (Exception e) {
            System.out.println("transMap2Bean2 Error " + e);
        }
    }

    public static Map<String, Object> transBean2Map(Object obj) {
        if (obj == null) {
            return new HashMap<>();
        }
        try {
            return new BeanMap(obj);
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }
        return null;
    }


    public static void main(String[] args) {
        UserDto userDto = new UserDto();
        userDto.setIdNum("aaa111");
        userDto.setUName("tom");
        userDto.setPassword("asdasd");
        userDto.setLoginDate(new Date());
        userDto.setNum(100);
        Map<String, Object> map = transBean2Map(userDto);
        System.out.println(map);

        UserDto userDto1 = new UserDto();
        transMap2Bean(map, userDto1);

        System.out.println(userDto1);


    }

}
