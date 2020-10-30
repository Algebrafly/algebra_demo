package com.algebra.demo.util.algorithm;

/**
 * @author al
 * @date 2020/10/28 16:58
 * @description 二叉排序树（二叉搜索树、二叉查找树）
 * 参考： https://www.jianshu.com/p/9f148caf2535
 * https://www.cnblogs.com/ysocean/p/8032642.html
 */
public class BinarySortTree {

    /**
     * 根节点
     */
    private Node root;

    /**
     * 查找节点
     *
     * @param key
     * @return
     */
    public Node find(int key) {
        Node current = root;
        while (current != null) {
            if (current.data > key) {
                current = current.leftChild;
            } else if (current.data < key) {
                current = current.rightChild;
            } else {
                return current;
            }
        }
        return null;
    }

    /**
     * 插入节点
     *
     * @param data 节点数据
     * @return 成功与否
     */
    public boolean insert(int data) {
        Node insertNode = new Node(data);
        if (root == null) {
            // 当前树是空树
            root = insertNode;
            return true;
        }
        Node current = root;
        Node parent = null;
        while (true) {
            parent = current;
            if (current.data > data) {
                // 如果当前节点大于插入节点，则搜寻当前节点的左子树
                current = current.leftChild;
                if (current == null) {
                    parent.leftChild = insertNode;
                    return true;
                }
            } else {
                // 如果当前节点小于插入节点，则搜寻当前节点的右子树
                current = current.rightChild;
                if (current == null) {
                    parent.rightChild = insertNode;
                    return true;
                }
            }
        }
    }


    //中序遍历
    public void infixOrder(Node current) {
        if (current != null) {
            infixOrder(current.leftChild);
            System.out.print(current.data + " ");
            infixOrder(current.rightChild);
        }
    }

    //前序遍历
    public void preOrder(Node current) {
        if (current != null) {
            System.out.print(current.data + " ");
            preOrder(current.leftChild);
            preOrder(current.rightChild);
        }
    }

    //后序遍历
    public void postOrder(Node current) {
        if (current != null) {
            postOrder(current.leftChild);
            postOrder(current.rightChild);
            System.out.print(current.data + " ");
        }
    }

    //找到最大值
    public Node findMax() {
        Node current = root;
        Node maxNode = current;
        while (current != null) {
            maxNode = current;
            current = current.rightChild;
        }
        return maxNode;
    }

    //找到最小值
    public Node findMin() {
        Node current = root;
        Node minNode = current;
        while (current != null) {
            minNode = current;
            current = current.leftChild;
        }
        return minNode;
    }

    // 删除节点
    public boolean delete(int key) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = false;
        //查找删除值，找不到直接返回false
        while (current.data != key) {
            parent = current;
            if (current.data > key) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
            if (current == null) {
                return false;
            }
        }
        //如果当前节点没有子节点
        if (current.leftChild == null && current.rightChild == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }
            return true;

            //当前节点有一个子节点，右子节点
        } else if (current.leftChild == null && current.rightChild != null) {
            if (current == root) {
                root = current.rightChild;
            } else if (isLeftChild) {
                parent.leftChild = current.rightChild;
            } else {
                parent.rightChild = current.rightChild;
            }
            return true;
            //当前节点有一个子节点，左子节点
        } else if (current.leftChild != null && current.rightChild == null) {
            if (current == root) {
                root = current.leftChild;
            } else if (isLeftChild) {
                parent.leftChild = current.leftChild;
            } else {
                parent.rightChild = current.leftChild;
            }
            return true;
        } else {
            //当前节点存在两个子节点
            Node successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.leftChild = successor;
            } else {
                parent.rightChild = successor;
            }
            successor.leftChild = current.leftChild;
        }
        return false;

    }

    // 后继节点也就是：比删除节点大的最小节点。
    // 1.后继节点是删除节点的右子节点
    // 2.后继节点是删除节点的右子节点的左子节点
    public Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }
        //后继节点不是删除节点的右子节点，将后继节点替换删除节点
        if (successor != delNode.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }

        return successor;
    }

    /**
     * 节点
     */
    static class Node {
        int data;
        Node leftChild;
        Node rightChild;
        boolean isDeleted;

        public Node(int data) {
            this.data = data;
        }

        public void display() {
            System.out.println(data);
        }
    }

}
