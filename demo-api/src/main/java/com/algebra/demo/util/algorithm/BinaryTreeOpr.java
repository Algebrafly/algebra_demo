package com.algebra.demo.util.algorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author al
 * @date 2020/10/26 16:35
 * @description 【小灰的算法之旅-java版实现】- 二叉树的遍历-1
 * 参考： https://www.cnblogs.com/ysocean/p/8032642.html
 */
public class BinaryTreeOpr {

    public static void main(String[] args) {

        LinkedList<Integer> inputList = new LinkedList<>(Arrays.asList(3, 2, 9, null, null, 10, null, null, 8, null, 4));
        TreeNode treeNode = createBinaryTree(inputList);
        System.out.println(treeNode);
        System.out.println("前序遍历：");
        preOrderTraverse(treeNode);
        System.out.println("\n中序遍历：");
        inOrderTraverse(treeNode);
        System.out.println("\n后序遍历：");
        postOrderTraverse(treeNode);

        System.out.println("\n非递归先序遍历：");
        preOrderTraverseWithStack(treeNode);
    }


    /**
     * 前序遍历-递归实现
     *
     * @param treeNode 二叉树
     */
    public static void preOrderTraverse(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.print(treeNode.data + " ");
        preOrderTraverse(treeNode.leftChild);
        preOrderTraverse(treeNode.rightChild);
    }

    /**
     * 前序遍历-栈实现
     *
     * @param root 二叉树
     */
    public static void preOrderTraverseWithStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            // 迭代访问节点的左孩子，放入栈
            while (treeNode != null) {
                System.out.print(treeNode.data+" ");
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }
            // 如果节点没有左孩子，则弹出栈顶节点，访问节点右孩子
            if(!stack.isEmpty()){
                treeNode = stack.pop();
                treeNode = treeNode.rightChild;
            }
        }

    }


    /**
     * 中序遍历
     *
     * @param treeNode 二叉树
     */
    public static void inOrderTraverse(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        inOrderTraverse(treeNode.leftChild);
        System.out.print(treeNode.data + " ");
        inOrderTraverse(treeNode.rightChild);
    }

    /**
     * 后序遍历
     *
     * @param treeNode 二叉树
     */
    public static void postOrderTraverse(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        postOrderTraverse(treeNode.leftChild);
        postOrderTraverse(treeNode.rightChild);
        System.out.print(treeNode.data + " ");
    }


    static class TreeNode {
        int data;
        TreeNode leftChild;
        TreeNode rightChild;

        TreeNode(int data) {
            this.data = data;
        }
    }

    /**
     * 构建二叉树 （简单方式）
     *
     * @param inputList 输入序列
     * @return BinaryTree
     */
    public static TreeNode createBinaryTree(LinkedList<Integer> inputList) {

        TreeNode node = null;
        if (inputList == null || inputList.isEmpty()) {
            return null;
        }
        Integer data = inputList.removeFirst();
        if (data != null) {
            node = new TreeNode(data);
            node.leftChild = createBinaryTree(inputList);
            node.rightChild = createBinaryTree(inputList);
        }
        return node;
    }


}
