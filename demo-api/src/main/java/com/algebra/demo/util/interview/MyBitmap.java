package com.algebra.demo.util.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/**
 * @author al
 * @date 2020/11/11 9:52
 * @description 【小灰的算法之旅-java版实现】- 用户标签匹配
 * 开发一个用户画像系统，实现用户信息的标签化。通过用户标签，我们可以对多样的用户群体 进行统计。
 * 例如统计用户的男女比例、统计喜欢旅游的用户数量等。
 * 解决方案：Bitmap 算法（位图算法）—— 内存中连续的二进制位（bit）所组成的数据结构
 * 1.数据结构：{标签 : 用户ID集合}
 * 2.位运算做匹配
 * 原理参考：
 * https://blog.csdn.net/kongmin_123/article/details/82222023
 * https://mp.weixin.qq.com/s/xxauNrJY9HlVNvLrL5j2hg
 * https://blog.csdn.net/qq_31622605/article/details/78041796
 * (代码解析)https://www.jianshu.com/p/6082a2f7df8e
 */
public class MyBitmap {


    /**
     * 每一个word是一个long类型元素，对应一个64位二进制数据
     */
    private long[] words;

    /**
     * Bitmap的位数大小
     */
    private int size;


    public MyBitmap(int size) {
        this.size = size;
        this.words = new long[getWordIndex(size - 1) + 1];
    }

    /**
     * 判断Bitmap某一位的状态
     *
     * @param bitIndex 位图中的第bitIndex位
     * @return boolean
     */
    public boolean getBit(int bitIndex) {
        if (bitIndex < 0 || bitIndex > size - 1) {
            throw new IndexOutOfBoundsException("超过Bitmap有效范围");
        }
        int wordIndex = getWordIndex(bitIndex);

        return (words[wordIndex] & (1L << bitIndex)) != 0;
    }

    public boolean contain(int bitIndex) {
        int wordIndex = getWordIndex(bitIndex);
        // num%64 得到在byte[index]的位置
//        int position = bitIndex & 0x3F;
        int position = bitIndex % 64;
        return (words[wordIndex] & (1L << position)) != 0;
    }

    /**
     * 把Bitmap某一位设置为true
     *
     * @param bitIndex 位图的第bitIndex位
     */
    public void setBit(int bitIndex) {
        if (bitIndex < 0 || bitIndex > size - 1) {
            throw new IndexOutOfBoundsException("超过Bitmap有效范围");
        }
        int wordIndex = getWordIndex(bitIndex);
        // TODO question-1：position的确定问题？
        words[wordIndex] |= (1L << bitIndex);
    }

    public void add(int bitIndex) {
        int wordIndex = getWordIndex(bitIndex);
        // num%64 得到在byte[index]的位置
        int position = bitIndex & 0x3F;
//        int position = bitIndex % 64;
        //将1左移position后，那个位置自然就是1，然后和以前的数据做|，这样，那个位置就替换成1了。
        // TODO question-1：position的确定问题？
        words[wordIndex] |= (1L << position);
    }

    /**
     * 定位Bitmap某一位所对应的word
     *
     * @param bitIndex 位图的第bitIndex位
     * @return 位置
     */
    private int getWordIndex(int bitIndex) {
        // 右移6位，相当于除以64（2^6）
        return bitIndex >> 6;
    }

    public long[] getWords() {
        return words;
    }

    /**
     * 清除当前位置数据
     *
     * @param bitIndex 数据
     */
    public void clear(int bitIndex) {
        // num/8得到byte[]的index
        int wordIndex = getWordIndex(bitIndex);

        // num%8得到在byte[index]的位置
        int position = bitIndex & 0x3F;

        //将1左移position后，那个位置自然就是1，然后对取反，再与当前值做&，即可清除当前的位置了.
        words[wordIndex] &= ~(1 << position);
    }

    /**
     * 二进制数据展示
     * @param row 展示元素个数
     */
    public void display(int row) {
        System.out.println("BitMap位图展示");
        for (int i = 0; i < row; i++) {
            List<Long> list = new ArrayList<>();
            long temp = words[i];
            for (long j = 0; j < 64; j++) {
                list.add(temp & 1);
                temp >>= 1;
            }
            System.out.println("a[" + i + "]" + list);
        }
    }

    // TODO BitMap and , or , xor 等运算 ...



    public static void main(String[] args) {

        // version 1
        MyBitmap bitmap = new MyBitmap(128);
        bitmap.setBit(63);
        bitmap.setBit(126);
        System.out.println(bitmap.getBit(63));
        System.out.println(bitmap.getBit(126));
        System.out.println(Arrays.toString(bitmap.getWords()));

        // version 2
        MyBitmap bitmap1 = new MyBitmap(256);
        bitmap1.add(1);
        bitmap1.add(63);
        bitmap1.add(126);
        bitmap1.add(128);
        System.out.println(bitmap1.contain(63));
        System.out.println(bitmap1.contain(126));
        System.out.println(Arrays.toString(bitmap1.getWords()));

        bitmap1.display(3);


        // Java BitSet Test
        BitSet bitSet = new BitSet(128);
        bitSet.set(63);
        bitSet.set(126);
        System.out.println(bitSet.get(63));
        System.out.println(bitSet.get(126));
        System.out.println(Arrays.toString(bitSet.toLongArray()));


        // Google BitSet Test
        com.googlecode.javaewah.datastructure.BitSet gBitSet = new com.googlecode.javaewah.datastructure.BitSet(128);
        gBitSet.set(63);
        gBitSet.set(126);
        System.out.println(gBitSet.get(63));
        System.out.println(gBitSet.get(126));
        System.out.println(gBitSet.getWord(1));


    }

}
