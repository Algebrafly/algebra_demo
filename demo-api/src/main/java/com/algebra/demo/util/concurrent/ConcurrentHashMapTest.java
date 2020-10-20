package com.algebra.demo.util.concurrent;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author al
 * @date 2020/10/20 11:22
 * @description
 */
public class ConcurrentHashMapTest {

    private static final ConcurrentHashMap<String, MethodCryptMetadata> METHOD_ENCRYPT_MAP = new ConcurrentHashMap<>();

    public static void testAbsent(String key){
        /*如果指定的键尚未与某个值关联，则尝试使用给定的映射函数计算其值，并将其输入此映射中，除非为null。
          整个方法调用是原子执行的，因此每个键最多可应用一次该功能。 在进行计算时，可能会阻止其他线程在此映射上进行的某些尝试的更新操作，
          因此计算应简短而简单，并且不得尝试更新此映射的任何其他映射。*/
        MethodCryptMetadata a = METHOD_ENCRYPT_MAP.computeIfAbsent(key, (k -> new MethodCryptMetadata("1","1")));
        System.out.println(a.toString());
        System.out.println(METHOD_ENCRYPT_MAP.toString());
    }
    public static void testIfPresent(String key){
        /*如果存在指定键的值，请尝试在给定键及其当前映射值的情况下计算新映射。
          整个方法调用是原子执行的。 在进行计算时，可能会阻止其他线程在此映射上进行的某些尝试的更新操作，
          因此计算应简短而简单，并且不得尝试更新此映射的任何其他映射。*/
        METHOD_ENCRYPT_MAP.put(key,new MethodCryptMetadata());
        MethodCryptMetadata a = METHOD_ENCRYPT_MAP.computeIfPresent(key, ((k,v) -> new MethodCryptMetadata("2","2")));
        System.out.println(a.toString());
        System.out.println(METHOD_ENCRYPT_MAP.toString());
    }


    public static void main(String[] args) {

//        testAbsent("1");
        testIfPresent("2");

    }


    public static class MethodCryptMetadata {

        private String id;

        private String name;

        public MethodCryptMetadata() {
        }

        public MethodCryptMetadata(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "MethodCryptMetadata{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}
