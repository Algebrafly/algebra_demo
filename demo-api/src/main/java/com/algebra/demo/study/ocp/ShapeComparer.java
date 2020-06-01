package com.algebra.demo.study.ocp;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author al
 * @date 2020/6/1 16:49
 * @description
 */
public class ShapeComparer implements Comparator {

    private static Map<Class<? extends Shape>, Integer> properties = new HashMap<>();

    /*
     * 定义图形优先级
     */
    static {
        properties.put(Circle.class,1);
        properties.put(Square.class,2);
    }

    /**
     * 获取图形优先级
     */
    private int priorityFor(Class clazz){
        if(properties.containsKey(clazz)){
            return properties.get(clazz);
        } else {
            return 0;
        }
//        return properties.getOrDefault(clazz, 0);
    }

    @Override
    public int compare(Object o1, Object o2) {
        Integer pri1 = priorityFor(o1.getClass());
        Integer pri2 = priorityFor(o2.getClass());
        return pri1.compareTo(pri2);
    }
}
