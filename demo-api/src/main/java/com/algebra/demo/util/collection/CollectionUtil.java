package com.algebra.demo.util.collection;

import java.util.*;

/**
 * @author al
 * @date 2020/5/18 16:31
 * @description
 */
public class CollectionUtil {

    /**
     * 求并集
     * @param arr1 数组-1
     * @param arr2 数组-2
     * @return 并集
     */
    public static String[] union(String[] arr1, String[] arr2) {
        Set<String> set = new HashSet<>();
        for (String str : arr1) {
            set.add(str);
        }
        for (String str : arr2) {
            set.add(str);
        }
        String[] result = {};
        return set.toArray(result);
    }

    /**
     * 求差集
     * @param arr1 数组-1
     * @param arr2 数组-2
     * @return 差集
     */
    public static String[] minus(String[] arr1, String[] arr2){
        LinkedList<String> list = new LinkedList<>();
        LinkedList<String> history = new LinkedList<>();
        String[] longerArr = arr1;
        String[] shorterArr = arr2;
        //找出较长的数组来减较短的数组
        if (arr1.length < arr2.length) {
            longerArr = arr2;
            shorterArr = arr1;
        }
        for (String str : longerArr) {
            if (!list.contains(str)) {
                list.add(str);
            }
        }
        for (String str : shorterArr) {
            if (list.contains(str)) {
                history.add(str);
                list.remove(str);
            } else {
                if (!history.contains(str)) {
                    list.add(str);
                }
            }
        }

        String[] result = {};
        return list.toArray(result);
    }

    /**
     * 求交集
     * @param arr1 数组-1
     * @param arr2 数组-2
     * @return 交集
     */
    public static String[] intersect(String[] arr1, String[] arr2) {
        Map<String, Boolean> map = new HashMap<>();
        LinkedList<String> list = new LinkedList<>();
        for (String str : arr1) {
            if (!map.containsKey(str)) {
                map.put(str, Boolean.FALSE);
            }
        }
        for (String str : arr2) {
            if (map.containsKey(str)) {
                map.put(str, Boolean.TRUE);
            }
        }

        for (Map.Entry<String, Boolean> e : map.entrySet()) {
            if (e.getValue().equals(Boolean.TRUE)) {
                list.add(e.getKey());
            }
        }

        String[] result = {};
        return list.toArray(result);
    }


    public static void main(String[] args) {

        String a = "1,2,3,4,5,";
        String b = "4,5,6,7,8,";
        String[] aAry = a.split(",");
        String[] bAry = b.split(",");

        String[] union = union(aAry, bAry);
        System.out.println(Arrays.toString(union));

        String[] minus = minus(aAry, bAry);
        System.out.println(Arrays.toString(minus));

        String[] intersect = intersect(aAry, bAry);
        System.out.println(Arrays.toString(intersect));

    }

}
