package com.algebra.demo.util.sort;

import java.util.Arrays;

/**
 * @author al
 * @date 2020/11/4 9:24
 * @description 【myself】-希尔排序
 * 参考： https://www.cnblogs.com/chengxiao/p/6104371.html
 */
public class ShellSort {

    /**
     * 在要排序的一组数中，根据某一增量分为若干子序列，并对子序列分别进行插入排序。
     * 然后逐渐将增量减小,并重复上述过程。直至增量为1,此时数据序列基本有序,最后进行插入排序。
     * ps.经过每一轮“跳跃式”插入排序之后，数组基本有序
     * @param array
     */
    public static void shellSort(int[] array) {

        int temp = 0;
        int incre = array.length;

        while (true) {
            incre = incre / 2;

            //根据增量分为若干子序列（组）
            for (int k = 0; k < incre; k++) {

                // 每组的插入排序
                for (int i = k + incre; i < array.length; i += incre) {
                    for (int j = i; j > k; j -= incre) {
                        if (array[j] < array[j - incre]) {
                            temp = array[j - incre];
                            array[j - incre] = array[j];
                            array[j] = temp;
                        } else {
                            break;
                        }
                    }
                }
            }

            if (incre == 1) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {4, 5, 1, 3, 9, 8, 6, 7, 0, 2};
        shellSort(array);
        System.out.println(Arrays.toString(array));
    }


    // <--------------------番外篇----------------------- />
    /**
     * 希尔排序 针对有序序列在插入时采用交换法
     * @param arr
     */
    public static void sort(int []arr){
        //增量gap，并逐步缩小增量
        for(int gap=arr.length/2;gap>0;gap/=2){
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            for(int i=gap;i<arr.length;i++){
                int j = i;
                while(j-gap>=0 && arr[j]<arr[j-gap]){
                    //插入排序采用交换法
                    swap(arr,j,j-gap);
                    j-=gap;
                }
            }
        }
    }

    /**
     * 希尔排序 针对有序序列在插入时采用移动法。
     * @param arr
     */
    public static void sort1(int []arr){
        //增量gap，并逐步缩小增量
        for(int gap=arr.length/2;gap>0;gap/=2){
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            for(int i=gap;i<arr.length;i++){
                int j = i;
                int temp = arr[j];
                if(arr[j]<arr[j-gap]){
                    while(j-gap>=0 && temp<arr[j-gap]){
                        //移动法
                        arr[j] = arr[j-gap];
                        j-=gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }
    /**
     * 交换数组元素
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int []arr,int a,int b){
        arr[a] = arr[a]+arr[b];
        arr[b] = arr[a]-arr[b];
        arr[a] = arr[a]-arr[b];
    }

}
