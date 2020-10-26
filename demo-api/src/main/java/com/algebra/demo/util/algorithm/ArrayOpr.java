package com.algebra.demo.util.algorithm;

/**
 * @author al
 * @date 2020/6/24 10:44
 * @description 【小灰的算法之旅-java版实现】- 数组操作
 */
public class ArrayOpr {


    public static void main(String[] args) {

        MyArray myArray = new MyArray(3);

        myArray.insert(3, 0);
        myArray.insert(7, 1);
        myArray.insert(9, 2);
        myArray.insert(10, 0);
        int del = myArray.deleted(0);
        System.out.println(del);
        myArray.outPutArray();

    }

    private static class MyArray {

        private int[] array;
        private int size;

        public MyArray(int capacity) {
            this.array = new int[capacity];
            size = 0;
        }

        public void insert(int element, int index) {
            // 判断下标是否超出范围
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("index out of bounds for your array: array.size = " + array.length);
            }

            // 如果实际元素达到数组容量上限，则对数组进行扩容
            if (size >= array.length) {
                resize();
            }

            // 从右往左，元素逐个向右移位一个单元
            for (int i = size - 1; i >= index; i--) {
                array[i + 1] = array[i];
            }

            // 新元素入位
            array[index] = element;

            size++;
        }

        public void resize() {
            int[] arrayNew = new int[array.length * 2];
            // 把旧数组数据复制到新数组
            System.arraycopy(array, 0, arrayNew, 0, array.length);
            array = arrayNew;
        }

        public int deleted(int index) {
            // 判断访问下标是否超出数组长度限制
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("index out of bounds for your array: array.size = " + array.length);
            }
            int delElement = array[index];
            for (int i = index; i < size; i++) {
                array[i] = array[i + 1];
            }
            // [warming]错误示范
//        for (int i = size - 1; i >= index; i--) {
//            array[i-1] = array[i];
//        }
            size--;
            return delElement;
        }

        public void outPutArray() {
            for (int i = 0; i < size; i++) {
                System.out.print(array[i] + ",");
            }
        }

    }

}


