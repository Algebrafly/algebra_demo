package com.algebra.demo.util.algorithm;

/**
 * @author al
 * @date 2020/10/26 11:37
 * @description 【小灰的算法之旅-java版实现】- 链表操作
 */
public class LinkedOpr {


    public static void main(String[] args) {
        MyLinked myLinked = new MyLinked();
        myLinked.insert(1,0);
        myLinked.insert(1,1);
        myLinked.insert(2,2);
        myLinked.insert(1,3);
        myLinked.insert(9,0);
        Node deleted = myLinked.remove(0);
        System.out.println(deleted);
        myLinked.output();
    }

    private static class MyLinked {
        // 头结点指针
        private Node head;
        // 尾节点指针
        private Node last;
        // 链表实际长度
        private int size;

        /**
         * 链表插入
         *
         * @param data  插入数据
         * @param index 插入位置
         */
        public void insert(int data, int index) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException(" 超出链表节点 范围！");
            }
            Node insertNode = new Node(data);
            if (size == 0) {
                // 空链表
                head = insertNode;
                last = insertNode;
            } else if (index == 0) {
                // 插入头部
                insertNode.next = head;
                head = insertNode;
            } else if (index == size) {
                // 插入尾部
                last.next = insertNode;
                last = insertNode;
            } else {
                // 中间插入
                Node prevNode = get(index - 1);
                insertNode.next = prevNode.next;
                prevNode.next = insertNode;
            }
            size++;
        }

        /**
         * 删除指定位置元素
         *
         * @param index 位置
         * @return 被删除元素
         */
        public Node remove(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException(" 超出链表节点 范围！");
            }
            Node removeNode;
            if (index == 0) {
                // 删除头结点
                removeNode = head;
                head = head.next;
            } else if (index == size - 1) {
                // 删除尾节点
                Node prevNode = get(index - 1);
                removeNode = prevNode.next;
                prevNode.next = null;
                last = prevNode;
            } else {
                // 删除中间节点
                Node prevNode = get(index - 1);
                Node nextNode = prevNode.next.next;
                removeNode = prevNode.next;
                prevNode.next = nextNode;
            }
            size--;
            return removeNode;
        }

        /**
         * 获取指定位置元素
         *
         * @param index 位置
         * @return element
         */
        public Node get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException(" 超出链表节点 范围！");
            }
            Node temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp;
        }

        public void output() {
            Node temp = head;
            while (temp != null) {
                System.out.print(temp.data+"->");
                temp = temp.next;
            }
        }

    }


    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }


}
