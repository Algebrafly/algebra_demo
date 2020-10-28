package com.algebra.demo.util.algorithm;

import java.util.*;

/**
 * @author al
 * @date 2020/10/26 16:35
 * @description 【小灰的算法之旅-java版实现】- 二叉树的遍历-1
 * 参考： https://www.cnblogs.com/ysocean/p/8032642.html
 * https://www.cnblogs.com/morethink/p/7265817.html
 * https://www.cnblogs.com/liuyang0/p/6271324.html
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

        System.out.println("\n非递归后续遍历：");
        postOrderTraverseWithStack(treeNode);
        System.out.println();
        postOrderTraverseWithStack2(treeNode);

        System.out.println("\n队列层序遍历：");
        levelOrderTraverse(treeNode);


        System.out.println("\n番外篇输出结果：------------------------->>>>");
        preOrderTraversal(treeNode);
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
     * 前/中序遍历-栈实现
     *
     * @param root 二叉树
     */
    public static void preOrderTraverseWithStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            // 迭代访问节点的左孩子，放入栈
            while (treeNode != null) {
                // [前序遍历]
//                System.out.print(treeNode.data+"->");
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }
            // 如果节点没有左孩子，则弹出栈顶节点，访问节点右孩子
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                // [中序遍历]
                System.out.print(treeNode.data + "->");
                treeNode = treeNode.rightChild;
            }
        }

    }

    /**
     * 后序遍历-栈实现 (辅助标志栈)
     *
     * @param root 二叉树
     */
    public static void postOrderTraverseWithStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        // 辅助栈
        Stack<Integer> temp = new Stack<>();
        // 在辅助栈里面表示左节点
        int left = 1;
        // 在辅助栈里面表示右节点
        int right = 2;
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            // 迭代访问节点的左孩子，放入栈，并在辅助栈中标记为左节点
            while (treeNode != null) {
                stack.push(treeNode);
                temp.push(left);
                treeNode = treeNode.leftChild;
            }
            // 如果是从右子节点返回父节点，则任务完成，将两个栈的栈顶弹出
            if (!stack.isEmpty() && temp.peek() == right) {
                temp.pop();
                System.out.print(stack.pop().data + "->");
            }

            // 如果是从左子节点返回父节点，则将其标记改为右子节点
            if (!stack.empty() && temp.peek() == left) {
                temp.pop();
                temp.push(right);
                treeNode = stack.peek().rightChild;
            }
        }

    }

    /**
     * 后序遍历-栈实现 (双栈)
     *
     * @param root 二叉树
     */
    public static void postOrderTraverseWithStack2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> temp = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            // 迭代访问节点的左孩子，放入栈，并在辅助栈中标记为左节点
            while (treeNode != null) {
                stack.push(treeNode);
                temp.push(treeNode);
                treeNode = treeNode.rightChild;
            }
            treeNode = stack.pop();
            treeNode = treeNode.leftChild;
        }
        while (!temp.isEmpty()) {
            System.out.print(temp.pop().data + "->");
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

    /**
     * 层序遍历-队列方式实现
     *
     * @param treeNode 二叉树
     */
    public static void levelOrderTraverse(TreeNode treeNode) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(treeNode);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.data + "->");
            if (node.leftChild != null) {
                queue.offer(node.leftChild);
            }
            if (node.rightChild != null) {
                queue.offer(node.rightChild);
            }
        }
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

    // <---------------------------------番外版本--------------------------------/>

    public static List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            list.add(root.data);
            System.out.print(root.data + " ");
            if (root.rightChild != null) {
                stack.push(root.rightChild);
            }
            if (root.leftChild != null) {
                stack.push(root.leftChild);
            }
        }
        return list;
    }

    public List<Integer> preOrderTraversalSimple(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                list.add(node.data);
                node = node.leftChild;
            } else {
                node = stack.pop();
                node = node.rightChild;
            }
        }
        return list;
    }


    public List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Map<TreeNode, Integer> map = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return list;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.peek();
            while (root.leftChild != null) {
                if (map.containsKey(root.leftChild)) {
                    break;
                }
                stack.push(root.leftChild);
                root = root.leftChild;
            }
            root = stack.pop();
            list.add(root.data);
            map.put(root, 1);
            if (root.rightChild != null) {
                stack.push(root.rightChild);
            }

        }
        return list;
    }

    public List<Integer> inOrderTraversalSimple(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.leftChild;
            } else {
                node = stack.pop();
                list.add(node.data);
                node = node.rightChild;
            }
        }
        return list;
    }

    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Map<TreeNode, Integer> map = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return list;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.peek();
            if (root.leftChild == null && root.rightChild == null) {//不含左右结点时，出栈
                root = stack.pop();
                list.add(root.data);
                map.put(root, 1);
            } else if ((root.leftChild != null && root.rightChild == null && map.containsKey(root.leftChild))
                    || (root.rightChild != null && root.leftChild == null && map.containsKey(root.rightChild))
                    || (root.leftChild != null && root.rightChild != null && map.containsKey(root.leftChild)
                    && map.containsKey(root.rightChild))) {
                //包含子节点，但是子节点被访问过，出栈
                root = stack.pop();
                list.add(root.data);
                map.put(root, 1);
            } else {
                while (root.leftChild != null) {
                    if (map.containsKey(root.leftChild)) {
                        break;
                    }
                    stack.push(root.leftChild);
                    root = root.leftChild;
                }
                if (root.rightChild != null) {
                    if (map.containsKey(root.rightChild)) {
                        break;
                    }
                    stack.push(root.rightChild);
                }
            }
        }
        return list;
    }

    public List<Integer> postOrderTraversalSimple(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                list.add(0, node.data);
                node = node.rightChild;
            } else {
                node = stack.pop();
                node = node.leftChild;
            }
        }
        return list;
    }

}
