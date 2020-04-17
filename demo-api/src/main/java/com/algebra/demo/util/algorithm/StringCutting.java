package com.algebra.demo.util.algorithm;

import java.util.Scanner;

/**
 * @author al
 * @date 2020/4/17 15:53
 * @description
 */
public class StringCutting {


    private static int n;
    private static String ss;
    /**
     * StringCut函数是输出字符串的前n个字节
     * */
    public static void stringCut(String[] str){
        int count=0;
        //汉字的正则表达式
        String zhengzeHanzi="[\u4e00-\u9fa5]";
        for (String s : str) {
            if (s.matches(zhengzeHanzi)) {
                //将每个字符与正则表达式进行匹配，返回true或者false。
                count += 2;
            } else {
                count += 1;
            }
            if (count < n) {
                System.out.println(s);
            } else if (count == n) {
                System.out.println(s);
            } else {
                break;
            }
        }
    }
    /**
     * SetValue()函数是将字符串ss转化成为字符串数组，因为matchs函数的参数是字符串而不是字符。
     * */
    public static String[] setValue(){
        String[] string=new String[ss.length()];
        for (int i=0;i<ss.length();i++){
            string[i]=ss.substring(i,i+1);
        }
        return string;
    }
    public static void main(String[] args) {
        System.out.println("please input one string here!"+"");
        Scanner in = new Scanner(System.in);
        ss=in.next();
        System.out.println("The string is: "+ss);
        System.out.println("please input one number here! ");
        n=in.nextInt();
        System.out.println("The number is: "+n);
        stringCut(setValue());
    }


}
