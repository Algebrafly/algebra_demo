package com.algebra.authentication.util.bpm;

import org.springframework.stereotype.Component;

/**
 * @author al
 * @date 2021/7/14 14:40
 * @description
 */
@Component
public class SpecialDeal {

    public String doubleToString(Double num){
        num = Math.sqrt(num);
        System.out.println("数字转换："+ num);
        return String.valueOf(num);
    }

}
