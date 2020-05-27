package com.algebra.demo.study.coursetemp;

/**
 * @author al
 * @date 2020/5/26 17:23
 * @description Object 的 equals 方法容易抛空指针异常，应使用常量或确定有值的对象来调用
 */
public class Test03 {


    public static boolean isNullStr(String str){
        return str.toLowerCase().trim().equals("null");
    }

    public static void main(String[] args) {

        System.out.println(isNullStr("null"));

        System.out.println(isNullStr(null));

    }

}
