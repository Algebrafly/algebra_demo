package com.algebra.demo.util.interview;

import org.checkerframework.checker.units.qual.K;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author al
 * @date 2020/11/13 9:44
 * @description 【myself】- 用LinkedHashMap实现LRU
 * 参考： https://blog.csdn.net/elricboa/article/details/78847305
 * （LRU实现和redis实现）https://blog.csdn.net/qq_35625303/article/details/79538532
 */
public class LRUByLinked<K,V> {

    private static final float hashLoadFactory = 0.75f;
    private LinkedHashMap<K,V> map;
    private int cacheSize;

    public LRUByLinked(int cacheSize) {
        this.cacheSize = cacheSize;
        int capacity = (int)Math.ceil(cacheSize / hashLoadFactory) + 1;
        map = new LinkedHashMap<K,V>(capacity, hashLoadFactory, true){
            private static final long serialVersionUID = 1;

            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > LRUByLinked.this.cacheSize;
            }
        };
    }

    public synchronized V get(K key) {
        return map.get(key);
    }

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    public synchronized void clear() {
        map.clear();
    }

    public synchronized int usedSize() {
        return map.size();
    }

    public void print() {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.print(entry.getValue() + "--");
        }
        System.out.println();
    }

}
