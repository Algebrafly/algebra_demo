package com.algebra.demo.util.interview;

/**
 * @author al
 * @date 2020/11/5 9:58
 * @description 【小灰的算法之旅-java版实现】- 判断链表是否有环
 * 检测一个链表是否有环，如果有环，则计算出环的长度以及入环的节点
 */
public class LinkedIsCycle {

    /**
     * 判断链表是否有环，如果有则返回
     *
     * @param head 头结点
     * @return 第一次相遇节点
     */
    public static Node isCycle(Node head) {
        Node p1 = head;
        Node p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                return p1;
            }
        }
        return null;
    }

    /**
     * 获取环长度
     *
     * @param ringMeetNode 第一次相遇节点
     * @return 环长度
     */
    public static int getLinkedRingLen(Node ringMeetNode) {
        int len = 0;
        Node fast = ringMeetNode;
        Node slow = ringMeetNode;
        while (true) {
            slow = slow.next;
            fast = fast.next.next;
            len++;
            if (slow == fast) {
                break;
            }
        }
        return len;
    }


    /**
     * 获取从链表头到入环点的距离
     * D = n(s1+s2) - s1
     * ps. D-从链表头到入环点的距离
     * s1-从入环点到第一次相遇点的距离
     * s2-从首次相遇点回到入环点的距离
     * n-快步指针绕环的圈数
     *
     * @param head         头结点（链表）
     * @param ringMeetNode 第一次相遇节点
     * @return 环连接点位置
     */
    public static int getLinkedMeetLen(Node head, Node ringMeetNode) {
        int len = 0;
        Node fast = ringMeetNode;
        Node slow = head;
        while (true) {
            slow = slow.next;
            fast = fast.next;
            len++;
            if (slow == fast) {
                break;
            }
        }
        return len;
    }

    /**
     * 获取入环节点
     *
     * @param head    链表
     * @param meetLen 距离D
     * @return 节点
     */
    public static Node getCycleNode(Node head, int meetLen) {
        if (meetLen <= 0) {
            return null;
        }
        Node tmp = head;
        for (int i = 0; i < meetLen; i++) {
            tmp = tmp.next;
        }
        return tmp;
    }


    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    public static boolean hasCycle(Node head) {
        if (head == null || head.next == null) {
            return false;
        }
        Node slow = head;
        Node fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;

        node9.next = node4;

        Node cycle = isCycle(node1);
        System.out.println(cycle == null ? null : cycle.toString());

        int linkedRingLen = getLinkedRingLen(cycle);
        int linkedMeetLen = getLinkedMeetLen(node1, cycle);
        System.out.println("环长度：" + linkedRingLen + "，第一次入环距离：" + linkedMeetLen);
        Node cycleNode = getCycleNode(node1, linkedMeetLen);
        System.out.println(cycleNode == null ? null : cycleNode.toString());


        System.out.println(hasCycle(node1));

    }

}
