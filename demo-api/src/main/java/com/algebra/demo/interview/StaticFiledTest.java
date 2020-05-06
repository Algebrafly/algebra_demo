package com.algebra.demo.interview;

/**
 * @author al
 * @date 2020/5/6 13:33
 * @description
 */
public class StaticFiledTest {

    private static int test = 1;

    public int getValue(){
        for (int i = 0; i < 5; i++ ) {
            test++;
        }
        return test;
    }

    public static void add(int i){
        i += 1;
    }

    public static void main(String[] args) {

        System.out.println(new StaticFiledTest().getValue());

        int[] i = new int[1];
        i[0] = 0;
        add(i[0]);
        System.out.println(i[0]);


    }

}
