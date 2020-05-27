package com.algebra.demo.study.coursetemp;

import java.util.*;

/**
 * @author al
 * @date 2020/5/26 17:23
 * @description 使用 Map 的方法 keySet()/values()/entrySet()返回集合对象时，不可以对其进行添
 * 加元素操作，否则会抛出 UnsupportedOperationException 异常
 */
public class Test06 {

    public static void testList(){
        String[] strAry = {"aa","bb","cc","11","22"};
        List<String> al = Arrays.asList(strAry);
        al.add("33");
        System.out.println(al.toString());
    }

    public static void testMap(){
        Map<String,String> map = new HashMap<>();
        map.put("1","a");
        map.put("2","b");
        map.put("3","c");
        Set<String> keySet = map.keySet();
        keySet.add("4");
        Collection<String> values = map.values();
        values.add("d");
    }

    public static void testCollections(){
        List<String> strings = Collections.singletonList("1232");
        strings.add("asd");
    }


    public static void main(String[] args) {

        testCollections();


    }

}
