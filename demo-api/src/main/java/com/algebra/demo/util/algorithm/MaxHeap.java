package com.algebra.demo.util.algorithm;

/**
 * @author al
 * @date 2020/10/30 11:28
 * @description 【小灰的算法之旅-java版实现】- 二叉堆(完整增删)
 */
public class MaxHeap<T extends Comparable<? super T>> {

    public static void main(String[] args) {
        Integer[] arr = {3, 5, 1, 7, 2, 9, 8, 0, 4, 6, 1, 3, 6, 1, 1};
        sort(arr);
    }


    private T[] data;
    private int size;
    private int capacity;


    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.data = (T[]) new Comparable[capacity + 1];
    }

    public MaxHeap(T[] arrays) {
        capacity = arrays.length;
        data = (T[]) new Comparable[capacity + 1];
        System.arraycopy(arrays, 0, data, 1, arrays.length);
        size = arrays.length;
        for (int i = size / 2; i >= 1; i--) {
            // 下沉操作
            shiftDown(i);
        }
    }

    /**
     * 插入元素
     *
     * @param item 元素
     */
    public void insert(T item) {
        size++;
        data[size] = item;
        shiftUp(size);
    }

    /**
     * 删除元素
     *
     * @return 删除的元素
     */
    public T popMax() {
        swap(1, size--);
        shiftDown(1);
        return data[size + 1];
    }


    public static <T extends Comparable<? super T>> void sort(T[] arr) {
        int len = arr.length;
        MaxHeap<T> maxHeap = new MaxHeap<>(arr);
        maxHeap.printAsTree();
        for (int i = len - 1; i >= 0; i--) {
            arr[i] = maxHeap.popMax();
        }
    }

    /**
     * 打印树状大顶堆
     */
    public void printAsTree() {
        //首先遍历第一行
        int lineNum = 1;
        //lines是堆的层数
        int lines = (int) (Math.log(size) / Math.log(2)) + 1;
        int spaceNum = (int) (Math.pow(2, lines) - 1);
        for (int i = 1; i <= size; ) {
            //因为在[1...size]左闭右闭区间存数据，data[0]不存数据

            //每层都是打印这个区间[2^(层数-1) ... (2^层数)-1]。如果堆里的数不够(2^层数)-1个，那就打印到size。所以取min((2^层数)-1,size).
            for (int j = (int) Math.pow(2, lineNum - 1); j <= Math.min(size, (int) Math.pow(2, lineNum) - 1); j++) {
                //打印spaceNum个空格
                printSpace(spaceNum);
                //打印数据
                System.out.printf("%3s", data[j]);
                //图片中绿色方框
                System.out.printf("%3s", "");
                //打印spaceNum个空格
                printSpace(spaceNum);
                i++;
            }
            lineNum++;
            spaceNum = spaceNum / 2;
            System.out.println();
        }
    }

    // <----------------------内部封装----------------/>

    public int size() {
        return this.size;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T seekMax() {
        return data[1];
    }

    public void swap(int i, int j) {
        if (i != j) {
            T temp = data[i];
            data[i] = data[j];
            data[j] = temp;
        }
    }

    public void shiftUp(int child) {
        while (child > 1 && data[child].compareTo(data[child / 2]) > 0) {
            swap(child, child / 2);
            child /= 2;
        }
    }

    public void shiftDown(int father) {
        while (true) {
            int lchild = father * 2;
            int rchild = father * 2 + 1;
            //这里赋不赋值无所谓，如果把下面这个return改成break,那就必须赋值了
            int newFather = father;

            //如果没有左、右孩子
            if (lchild > size) {
                return;
            } else if (rchild > size) {
                //如果没有右孩子
                newFather = max(father, lchild);
            } else {//如果有左、右孩子
                newFather = max(father, lchild, rchild);
            }

            //如果原父结点就是三者最大，则不用继续整理堆了
            if (newFather == father) {
                return;
            } else {
                //父节点不是最大，则把大的孩子交换上来，然后继续往下堆调整，直到满足大根堆为止
                swap(newFather, father);
                //相当于继续shiftDown(newFather)。假如newFather原来是father的左孩子，那就相当于shiftDown(2*father)
                father = newFather;
            }
        }
    }

    /**
     * @param a data数组中某个元素的下角标
     * @param b data数组中某个元素的下角标
     * @return 哪个元素大就返回哪个的下角标
     */
    private int max(int a, int b) {
        if (data[a].compareTo(data[b]) < 0) {//如果data[b]大
            return b;//返回b
        } else {//如果data[a]大
            return a;//返回a
        }
    }

    /**
     * @param a data数组中某个元素的下角标
     * @param b data数组中某个元素的下角标
     * @param c data数组中某个元素的下角标
     * @return 哪个元素大就返回哪个的下角标
     */
    private int max(int a, int b, int c) {
        int biggest = max(a, b);
        biggest = max(biggest, c);
        return biggest;
    }

    public static void printArr(Object[] arr) {
        for (Object o : arr) {
            System.out.print(o);
            System.out.print("\t");
        }
        System.out.println();
    }

    public void printSpace(int n) {//打印n个空格(在这里用‘\t’来代替)
        for (int i = 0; i < n; i++) {
            System.out.printf("%3s", "");
        }
    }

}
