package com.algebra.demo.study.strategyfactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author al
 * @date 2020/1/19 9:57
 * @description 策略的工厂模式
 */
public class StrategyFactory {

    private Map<String,SaleStrategy> map;

    public StrategyFactory(){
        List<SaleStrategy> strategies = new ArrayList<>();

        strategies.add(new OrdinaryStrategy());
        strategies.add(new SilverStrategy());
        strategies.add(new GoldStrategy());
        strategies.add(new PlatinumStrategy());
        // 转换map存储
        map = strategies.stream().collect(Collectors.toMap(SaleStrategy::getType, strategy -> strategy));
    }

    public static class Holder {
        public static StrategyFactory instance = new StrategyFactory();
    }

    public static StrategyFactory getInstance() {
        return Holder.instance;
    }

    /**
     * 根据客户类型获取打折策略
     * @param key 客户类型
     * @return 策略类
     */
    public SaleStrategy get(String key) throws Exception {
        if (key != null && !"".equals(key)) {
            key = key.toUpperCase();
            if(map.containsKey(key)){
                return map.get(key);
            } else {
                throw new Exception("not found the strategy by key: " + key);
            }
        } else {
            throw new Exception("key is not allowed null or empty string");
        }
    }

}
