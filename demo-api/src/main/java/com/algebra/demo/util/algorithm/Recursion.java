package com.algebra.demo.util.algorithm;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author al
 * @date 2020/10/27 9:32
 * @description 递归情景测试
 */
public class Recursion {

    public static void main(String[] args) throws Exception {
//        File f = new File("E:\\log");
//        listAllFile(f);

//        System.out.println(factorial(4));

//        System.out.println(fib(6));

//        int[] source = {49, 38, 65, 97, 76, 13, 27};
//        mergeSort(source, 0, source.length - 1);
//        System.out.println(Arrays.toString(source));

        tower(3, 'A', 'B', 'C');
        System.out.println(count);
    }

    /**
     * 示例一：递归打印文件夹下所有文件和目录
     *
     * @param rootFile 根目录
     */
    public static void listAllFile(File rootFile) {
        File[] files = rootFile.listFiles();
        if (files == null) {
            System.out.println("文件夹下文件为空");
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("目录名:" + file.getName());
                listAllFile(file);
            } else {
                System.out.println("文件名:" + file.getName());
            }
        }

    }

    /**
     * 示例二：求阶乘
     *
     * @param n 阶乘根数
     * @return 阶乘值
     * @throws Exception 负数排除运算
     */
    public static int factorial(int n) throws Exception {
        if (n < 0) {
            throw new Exception("负数不能参与运算");
        } else if (n == 1 || n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    /**
     * 示例三：斐波纳挈数列
     *
     * @param n 最大数
     * @return 数列个值
     * @throws Exception 负数
     */
    public static int fib(int n) throws Exception {
        if (n < 0) {
            throw new Exception("参数不能为负！");
        } else if (n == 0 || n == 1) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }


    /**
     * 示例四：归并排序
     * 递归过程是：在递进的过程中拆分数组，在回归的过程合并数组
     *
     * @param source
     * @param first
     * @param last
     */
    public static void mergeSort(int[] source, int first, int last) {
        if (first < last) {
            int mid = (first + last) / 2;
            mergeSort(source, first, mid);    //归并排序前半个子序列
            mergeSort(source, mid + 1, last); //归并排序后半个子序列
            merge(source, first, mid, last);    //在回归过程中合并
        }
    }


    private static int count = 0;

    /**
     * 示例五：汉诺塔问题
     * n个塔从source经过temp最终全部移动到target
     * 执行次数： 2^n-1
     *
     * @param n      圆饼数量
     * @param source 源塔
     * @param temp   中间塔
     * @param target 目标塔
     */
    public static void tower(int n, char source, char temp, char target) {
        if (n == 1) {
            move(source, target);
        } else {
            tower(n - 1, source, target, temp);
            move(source, target);
            tower(n - 1, temp, source, target);
        }
    }

    /**
     * 示例六：模拟求解N连环
     *
     * @param node 环对象
     * @param list 环列表
     * @param n    环数量
     */
    public static void play(Node[] node, ArrayList<Node> list, int n) {
        boolean deal = false;

        if (n == 1) {
            if (node[n].state == 1) {
                move(node[n]);// move the 1st;
                node[n].state = 0;
                list.remove(list.size() - 1);
            } else {
                move(node[n]);
                node[n].state = 1;
                list.add(node[n]);
            }
        } else {
            while (!deal) {
                if (node[n - 1].state == 1) {//前一环在上
                    if (list.get(list.size() - 1).num == n - 1)//前一环为栈顶
                    {
                        if (node[n].state == 1) {
                            move(node[n]);
                            node[n].state = 0;
                            deal = true;
                            list.remove(list.size() - 2);
                        } else {
                            move(node[n]);
                            node[n].state = 1;
                            deal = true;
                            list.add(list.size() - 1, node[n]);
                        }
                    } else//前一环在上，但是前一环不是栈顶
                    {
                        int index = 1;
                        for (int i = n - 2; i > 0; i--)//找到前一环之前的所有在上的环中最大的一个。
                        {
                            if (node[i].state == 1) {
                                index = i;
                                break;
                            }
                        }
                        play(node, list, index);//将前一环之前的在上的最大的一环移走
                    }
                }
                //-------------------------------------------------------------------------
                else if (node[n - 1].state == 0) {//前一环不在上

                    play(node, list, n - 1);
                }
            }
        }
    }

    public static void move(Node node) {
        if (node.state == 1) {
            System.out.println("down " + node.num);
        } else {
            System.out.println("up " + node.num);
        }
    }

    static class Node {
        // 环号
        int num;
        // 环状态（up or down）
        int state;

        Node(int num, int state) {
            this.num = num;
            this.state = state;
        }
    }

    public static void move(char s, char t) {
        System.out.println("move " + s + " to " + t);
        count++;
    }

    private static void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = a[j++];
        }
        // 把新数组中的数覆盖nums数组
        for (int x = 0; x < temp.length; x++) {
            a[x + low] = temp[x];
        }
    }

}
