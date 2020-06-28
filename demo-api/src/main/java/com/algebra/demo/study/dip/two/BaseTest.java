package com.algebra.demo.study.dip.two;

/**
 * @author al
 * @date 2020/6/28 10:08
 * @description
 */
public class BaseTest {

    public static void main(String[] args) {
        InputModule input = StuScoreList.getInputModule();
        CountModule count = StuScoreList.getCountModule();
        PrintModule print = StuScoreList.getPrintModule();
        input.insert();
        count.countTotalScore();
        print.printStuInfo();
        //print.delete();
    }

}
