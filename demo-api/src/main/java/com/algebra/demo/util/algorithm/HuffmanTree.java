package com.algebra.demo.util.algorithm;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author al
 * @date 2020/4/8 10:06
 * @description 构建哈夫曼树（给定叶子节点及其权重值大小后，构造出来的树的带权路径长度最小的二叉树）
 * 实现思路：
 *      为了保证结点队列当中的结点始终按照权值升序排列，我们使用了优先队列PriorityQueue；
 *      静态内部类Node需要实现比较接口，重写compareTo方法，以保证Node对象在进入队列时按照权值来比较。
 *      时间复杂度 O(nlogn)
 */
public class HuffmanTree {

    private Node root;

    private Node[] nodes;

    public void createHuffmanTree (int[] weights) {
        // 有限队列，辅助创建霍夫曼树
        Queue<Node> nodeQueue = new PriorityQueue<>();

        nodes = new Node[weights.length];

        // 构建森林，初始化nodes数组
        for (int i = 0; i < weights.length; i++) {
            nodes[i] = new Node(weights[i]);
            nodeQueue.add(nodes[i]);
        }

        // 主循环，当节点队列只剩一个节点时候结束
        while (nodeQueue.size() > 1) {
            // 从节点队列选择权值最小的两个节点
            Node left = nodeQueue.poll();
            Node right = nodeQueue.poll();

            // 创建新节点，作为两个节点的父节点
            Node parent = new Node(left.weight + right.weight, left, right);
            nodeQueue.add(parent);
        }

        root = nodeQueue.poll();
    }


    /**
     * 按照前序遍历输出
     * @param head
     */
    public void output(Node head){
        if(head == null){
            return;
        }
        System.out.println(head.weight);

        output(head.lChild);
        output(head.rChild);
    }


    public static class Node implements Comparable<Node> {

        int weight;

        Node lChild;

        Node rChild;

        public Node (int weight) {
            this.weight = weight;
        }

        public Node (int weight, Node lChild, Node rChild) {
            this.weight = weight;
            this.lChild = lChild;
            this.rChild = rChild;
        }


        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }


    public static void main(String[] args) {

        int[] weights = {2,3,7,9,18,25};

        HuffmanTree huffmanTree = new HuffmanTree();

        huffmanTree.createHuffmanTree(weights);

        huffmanTree.output(huffmanTree.root);


    }

}
