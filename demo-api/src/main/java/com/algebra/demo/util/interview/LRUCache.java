package com.algebra.demo.util.interview;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author al
 * @date 2020/11/12 17:42
 * @description 【小灰的算法之旅-java版实现】- LRU算法
 * java中LinkedHashMap的实现
 * Redis 缓存刷新机制
 */
public class LRUCache {

    private Node head;

    private Node end;

    /**
     * 缓存上限
     */
    private int limit;

    private HashMap<String, Node> hashMap;


    public LRUCache(int limit) {
        this.limit = limit;
        this.hashMap = new HashMap<>();
    }

    public String get(String key) {
        Node node = hashMap.get(key);
        if (node == null) {
            return null;
        }
        // 刷新节点位置到end
        refreshNode(node);
        return node.value;
    }

    public void put(String key, String value) {
        Node node = hashMap.get(key);
        if (node == null) {
            // key不存在，则插入key-value
            if (hashMap.size() >= limit) {
                // 移除LRU（least recently used）节点
                String oldKey = removeNode(head);
                hashMap.remove(oldKey);
            }
            node = new Node(key, value);
            addNode(node);
            hashMap.put(key, node);

        } else {
            // key不存在，则刷新key-value
            node.value = value;
            refreshNode(node);
        }

    }

    public void remove(String key) {
        Node node = hashMap.get(key);
        removeNode(node);
        hashMap.remove(key);
    }


    /**
     * 刷新被访问的节点位置
     *
     * @param node 被访问的节点
     */
    private void refreshNode(Node node) {
        // 如果被访问的是尾结点，则无需移动节点
        if (node == end) {
            return;
        }
        // 移除节点
        removeNode(node);
        // 重新插入节点
        addNode(node);
    }

    /**
     * 移除节点
     *
     * @param node 要移除的节点
     * @return 移除的节点值
     */
    private String removeNode(Node node) {
        if (node == head && node == end) {
            // 移除唯一的节点
            head = null;
            end = null;
        } else if (node == end) {
            // 移除尾结点
            end = end.pre;
            end.next = null;
        } else if (node == head) {
            // 移除头结点
            head = head.next;
            head.pre = null;
        } else {
            // 移除中间节点
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.key;
    }

    /**
     * 尾部插入节点
     *
     * @param node 节点
     */
    private void addNode(Node node) {
        if (end != null) {
            end.next = node;
            node.pre = end;
            node.next = null;
        }
        end = node;
        if (head == null) {
            head = node;
        }
    }

    public void print(){
        Node temp = head;
        while (temp != null){
            System.out.printf("Node{%s:%s}->", temp.key, temp.value);
            temp = temp.next;
        }
        System.out.println();
    }

    static class Node {
        Node pre;
        Node next;
        String key;
        String value;

        Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }


    public static void main(String[] args) {

        LRUCache lruCache = new LRUCache(5);
        lruCache.put("001","user-001");
        lruCache.put("002","user-002");
        lruCache.put("003","user-003");
        lruCache.put("004","user-004");
        lruCache.put("005","user-005");
        System.out.println("初始化打印：");
        lruCache.print();

        System.out.println(lruCache.get("001"));
        System.out.println("第一次查询后打印：");
        lruCache.print();

        lruCache.put("004","004更新信息");
        lruCache.put("006","新增user-006");
        System.out.println("更新/插入信息后打印：");
        lruCache.print();

        System.out.println(lruCache.get("001"));
        System.out.println(lruCache.get("006"));
        System.out.println("第二次查询后打印：");
        lruCache.print();


        // java LinkedHashMap
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("a","user-a");
        map.put("b","user-b");
        map.put("c","user-c");
        map.put("d","user-d");
        map.put("e","user-e");
        System.out.println(map.toString());

        System.out.println(map.get("b"));
        System.out.println(map.toString());

    }

}
