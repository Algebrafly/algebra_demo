package com.algebra.demo.util.sort;

import java.util.*;

/**
 * @author al
 * @date 2020/11/3 15:06
 * @description 【小灰的算法之旅-java版实现】- 桶排序
 */
public class BucketSort {

    /**
     * 桶排序 v1
     * @param array 原数组
     * @return 排序后数组
     */
    public static double[] bucketSortV1(double[] array) {
        //1.得到数列的最大值和最小值，并计算出差值
        double max = array[0];
        double min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        double d = max - min;
        //2.初始化桶
        int bucketNum = array.length;
        List<LinkedList<Double>> bucketList = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<>());
        }

        //3.遍历原始数组，将每个元素放入桶中
        for (int i = 0; i < array.length; i++) {
            // explain:
            int num = (int) ((array[i] - min) * (bucketNum - 1) / d);
            System.out.println("元素："+array[i]+"被分配到桶："+num+"号中...");
            bucketList.get(num).add(array[i]);
        }

        System.out.println("创建的桶："+bucketList.toString());

        //4.对每个桶内部进行排序
        for (int i = 0; i < bucketList.size(); i++) {
            Collections.sort(bucketList.get(i));
        }

        //5.输出全部元素
        double[] sortedArray = new double[array.length];
        int index = 0;
        for (LinkedList<Double> doubles : bucketList) {
            for (Double element : doubles) {
                sortedArray[index] = element;
                index++;
            }
        }

        return sortedArray;
    }


    public static void main(String[] args) {

        double[] array = new double[] {4.12,6.421,0.0023,3.0,2.123,8.122,4.12, 10.09};

        double[] sortedArray = bucketSortV1(array);

        System.out.println(Arrays.toString(sortedArray));

    }

}
