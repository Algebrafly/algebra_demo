package com.algebra.demo.util.algorithm;

/**
 * @author al
 * @date 2020/6/24 10:44
 * @description 【小灰的算法之旅-java版实现】- 数组操作
 */
public class ArrayOpr {


    public static void main(String[] args) {

        MyArray myArray = new MyArray(10);

        myArray.insert(3,0);
        myArray.insert(7,1);
        myArray.insert(9,2);
        myArray.insert(10,1);

        myArray.outPutArray();

    }

}

class MyArray{

    private int[] array;
    private int size;

    public MyArray(int capacity){
        this.array = new int[capacity];
        size = 0;
    }

    public void insert(int element, int index){
        // 判断下标是否超出范围
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("index out of bounds for your array: array.size = " + array.length);
        }

        // 从右往左，元素逐个向右移位一个单元
        for(int i = size - 1; i >= index; i--){
            array[i+1] = array[i];
        }

        // 新元素入位
        array[index] = element;

        size ++;
    }

    public void outPutArray(){
        for (int i = 0; i<size; i++) {
            System.out.print(array[i]+",");
        }
    }

}
