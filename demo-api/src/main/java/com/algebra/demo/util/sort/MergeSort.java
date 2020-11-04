package com.algebra.demo.util.sort;

/**
 * @author al
 * @date 2020/11/4 13:47
 * @description 【myself】-归并排序
 * 参考：https://www.jianshu.com/p/33cffa1ce613
 * 分治法的一个非常典型的应用
 */
public class MergeSort {

    public static void mergeSort(int a[],int first,int last,int temp[]){

        if(first < last){
            int middle = (first + last)/2;
            //左半部分排好序
            mergeSort(a,first,middle,temp);
            //右半部分排好序
            mergeSort(a,middle+1,last,temp);
            //合并左右部分
            mergeArray(a,first,middle,last,temp);
        }
    }

    /**
     * 合并 ：将两个序列a[first-middle],a[middle+1-end]合并
     * 两边的数组挨个元素进行比较，将较小的放入temp数组，
     * 然后合并成一个有序临时子数组，
     * 最后将子数组的元素一一放回原数组
     * @param a
     * @param first
     * @param middle
     * @param end
     * @param temp
     */
    public static void mergeArray(int a[],int first,int middle,int end,int temp[]){
        int i = first;
        int m = middle;
        int j = middle+1;
        int n = end;
        int k = 0;
        while(i<=m && j<=n){
            if(a[i] <= a[j]){
                temp[k] = a[i];
                k++;
                i++;
            }else{
                temp[k] = a[j];
                k++;
                j++;
            }
        }
        while(i<=m){
            temp[k] = a[i];
            k++;
            i++;
        }
        while(j<=n){
            temp[k] = a[j];
            k++;
            j++;
        }

        for(int ii=0;ii<k;ii++){
            a[first + ii] = temp[ii];
        }
    }

}
