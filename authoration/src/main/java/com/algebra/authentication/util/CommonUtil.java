package com.algebra.authentication.util;

import org.slf4j.MDC;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author al
 * @date 2020/7/7 15:28
 * @description
 */
public class CommonUtil {

    /**
     * 创建以时间为依据的流水序号
     * @param remark 序号特殊标识
     * @return 流水序号
     */
    public static String generateSerialNumber(String remark){
        String result = null;
        String  random = String.format("%03d", ThreadLocalRandom.current().nextInt(1,999));
        if(remark != null && !"".equals(remark)){
            String  nowTime = remark + "_" + System.currentTimeMillis();
            result =  nowTime + random;
        } else {
            result = System.currentTimeMillis()+random;
        }
        return result;
    }

    /**
     * 创建以UUID为依据的唯一主键
     * @param remark 序号特殊标识
     * @return 唯一主键
     */
    public static String createPrimaryKey(String remark){
        return remark +"_"+UUID.randomUUID().toString().replaceAll("-","");
    }

    public static boolean isNullOrBlank(String str) {
        return str == null || "".equals(str);
    }

    public static String getMdcKey(String mdcKey){
        String mdcValue = MDC.get(mdcKey);
        if(mdcValue == null){
            mdcValue = "default";
        }
        return mdcValue;
    }

    /**
     * 函数
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
//        Predicate<T> p = new Predicate<T>() {
//            @Override
//            public boolean test(T t) {
//                return seen.add(keyExtractor.apply(t));
//            }
//        };
        return t -> seen.add(keyExtractor.apply(t));
    }


    public static void main(String[] args) {
        System.out.println(generateSerialNumber("asd"));
    }



}
