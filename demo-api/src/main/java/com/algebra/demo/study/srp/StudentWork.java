package com.algebra.demo.study.srp;

/**
 * @author al
 * @date 2020/6/28 9:11
 * @description
 */
public class StudentWork implements IStudentWork {

    @Override
    public void lifeGuide() {
        ICounselorWork counselorWork = new CounselorWork();
        counselorWork.classManager();
        counselorWork.attendanceStatistics();
        counselorWork.feeCollection();

    }

    @Override
    public void learningGuide() {
        ITeacherWork teacherWork = new TeacherWork();
        teacherWork.learningGuide();
        teacherWork.researchGuidance();
    }


    public static void main(String[] args) {

        IStudentWork studentWork = new StudentWork();

        studentWork.learningGuide();
        System.out.println("--------------------------");
        studentWork.lifeGuide();

    }
}
