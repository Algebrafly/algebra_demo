package com.algebra.demo.util.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author al
 * @date 2020/10/27 13:40
 * @description
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class XbLinkedBinaryTree<T> {

    private XbLinkedBinaryTree<T> leftNode;
    private T data;
    private XbLinkedBinaryTree<T> rightNode;
    /**
     * 深度 : 根节点到这个节点所经历的边的个数
     * 从上先下递增,根节点深度为0
     */
    private int depth;
    /**
     * 层 : 节点的深度 + 1
     */
    private int level;
    /**
     * 高度 : 节点到叶子节点的最长路径(边数)
     * 从下向上递增,最下面高度为0
     */
    private int height;
    /**
     * 若使用数组存储二叉树时,此元素在数组中位置的下标
     */
    private int index;

    /**
     * 使用一组数据随机的构建一个二叉树
     *
     * @param t 原始数据
     * @return 二叉树
     */
    public static <T> XbLinkedBinaryTree<T> build(T[] t) {
        XbLinkedBinaryTree<T> result = null;
        if (t.length > 0) {
            result = XbLinkedBinaryTree.build(0, t.length - 1, t, 0, 1);
        }
        return result;
    }

    private static <T> XbLinkedBinaryTree<T> build(int left, int right, T[] t, int depth, int index) {
        XbLinkedBinaryTree<T> result = null;
        int length = right - left;
        if (length >= 0) {
            /*  选择此节点的数据索引位置 */
            int pickNumber = RandomUtils.nextInt(left, right + 1);
            XbLinkedBinaryTree<T> leftTree = XbLinkedBinaryTree.build(left, pickNumber - 1, t, (depth + 1), index * 2);
            XbLinkedBinaryTree<T> rightTree = XbLinkedBinaryTree.build(pickNumber + 1, right, t, (depth + 1), (index * 2 + 1));
            int leftTreeHeight = leftTree == null ? 0 : leftTree.getHeight() + 1;
            int rightTreeHeight = rightTree == null ? 0 : rightTree.getHeight() + 1;
            result = new XbLinkedBinaryTree<T>(leftTree, t[pickNumber], rightTree, depth, (++depth), Math.max(leftTreeHeight, rightTreeHeight), index);
        }
        return result;
    }

    public String prettyToString(int unitCharsLength) {
        /*  单个字符的组成方式 */
        String unitChars = Stream.generate(() -> " ").limit(unitCharsLength).collect(Collectors.joining());
        String lineString = Stream.generate(() -> unitChars).limit((int) Math.pow(2, this.height + 2)).collect(Collectors.joining());
        /*  把二叉树按层打印出来 */
        List<XbLinkedBinaryTree<T>> dataList = new ArrayList<>();
        dataList.add(this);
        Stream.Builder<String> builder = Stream.builder();
        Optional<XbLinkedBinaryTree<T>> any;
        while ((any = dataList.stream().findAny()).isPresent()) {
            int currentNodeHeight = this.getHeight() - any.get().getDepth();
            int leftLen = (int) Math.pow(2, currentNodeHeight);
            int stepLen = leftLen * 2;
            StringBuffer lineStringBuffer = new StringBuffer(lineString);
            int upLevelCount = (int) Math.pow(2, any.get().getDepth()) - 1;
            dataList = dataList.stream().flatMap(s -> {
                String stringData = s.getData().toString();
                /*  计算此元素在这一行中是第几个元素,按照满二叉树计算 即: null ,null , 2 ,3 ,则, 3 是第四个元素 */
                int lineIndex = s.getIndex() - upLevelCount;
                /*  计算这个元素的首个字符在这一行内是第几个位置 */
                int start = ((lineIndex - 1) * stepLen + leftLen) * unitChars.length();
                lineStringBuffer.replace(start, start + stringData.length(), stringData);
                Stream<XbLinkedBinaryTree<T>> nodeStream;
                nodeStream = Stream.of(s.getLeftNode(), s.getRightNode()).filter(Objects::nonNull);
                return nodeStream;
            }).collect(Collectors.toList());
            builder.add(lineStringBuffer.toString()).add("\n");
        }
        return builder.build().collect(Collectors.joining());
    }

    public static void main(String[] args) {
        Integer[] objects = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).toArray(Integer[]::new);
        XbLinkedBinaryTree<Integer> build = XbLinkedBinaryTree.build(objects);
        System.out.println(build.prettyToString(2));
    }


}
