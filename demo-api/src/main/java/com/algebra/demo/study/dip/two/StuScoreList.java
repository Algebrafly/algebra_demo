package com.algebra.demo.study.dip.two;

/**
 * @author al
 * @date 2020/6/28 10:05
 * @description
 */
public class StuScoreList implements InputModule, CountModule, PrintModule {

    private StuScoreList() {
    }

    public static InputModule getInputModule() {
        return new StuScoreList();
    }

    public static CountModule getCountModule() {
        return new StuScoreList();
    }

    public static PrintModule getPrintModule() {
        return new StuScoreList();
    }

    @Override
    public void insert() {
        System.out.println("输入模块的insert()方法被调用！");
    }

    @Override
    public void delete() {
        System.out.println("输入模块的delete()方法被调用！");
    }

    @Override
    public void modify() {
        System.out.println("输入模块的modify()方法被调用！");
    }

    @Override
    public void countTotalScore() {
        System.out.println("统计模块的countTotalScore()方法被调用！");
    }

    @Override
    public void countAverage() {
        System.out.println("统计模块的countAverage()方法被调用！");
    }

    @Override
    public void printStuInfo() {
        System.out.println("打印模块的printStuInfo()方法被调用！");
    }

    @Override
    public void queryStuInfo() {
        System.out.println("打印模块的queryStuInfo()方法被调用！");
    }
}
