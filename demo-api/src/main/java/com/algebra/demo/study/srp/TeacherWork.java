package com.algebra.demo.study.srp;

/**
 * @author al
 * @date 2020/6/28 9:13
 * @description
 */
public class TeacherWork implements ITeacherWork {
    @Override
    public void learningGuide() {
        System.out.println("学习指导");
    }

    @Override
    public void researchGuidance() {
        System.out.println("科研指导");
    }
}
