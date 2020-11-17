package com.algebra.demo.util.interview;

import java.util.Arrays;

/**
 * @author al
 * @date 2020/11/17 10:12
 * @description
 */
public class GraphMinDistance {

    private static final int[][] GRAPH = new int[][]{
            {0, 3, 2, 0},
            {0, 0, 0, 1},
            {0, 0, 0, 6},
            {0, 0, 0, 0},
    };

    private static Node[] node = new Node[4];

    private static void initNode() {
        for (int i = 0; i < node.length; i++) {
            node[i] = new Node(i);
        }
    }

    public static int searchMinDistance() {
        initNode();
        //从矩阵a的第一行开始，一行行找相连的节点
        for (int i = 0; i < GRAPH.length; i++) {
            for (int j = i+1; j < GRAPH[i].length; j++) {
                //找到了相连节点
                if (GRAPH[i][j] != 0) {
                    //上一个节点的最短路径的值+与下一个节点相连路径上的值;
                    int d = node[i].value;
                    d = d + GRAPH[i][j];
                    //判断是否比原先的值要小，如果小就将0-j节点的长度替换
                    if (d < node[i].value) {
                        node[i].value = d;
                        //记录前一个节点的序号
                        node[i].parent = i;
                    }
                }
            }
        }
        return node[3].value;
    }



    public static void main(String[] args) {

        System.out.println(searchMinDistance());

    }


    static class Node {
        int number;
        //value是指从0到这个节点总共要走多远，执行算法前将value的值初始化为无穷大
        int value;
        int parent;

        Node(int number) {
            this.number = number;
            this.value = 999;
        }
    }

}
