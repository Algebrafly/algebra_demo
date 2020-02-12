package com.algebra.demodatasources.web;

import com.algebra.demo.util.base.WebApiResult;
import com.algebra.demodatasources.entity.domain.Lesson;
import com.algebra.demodatasources.entity.domain.Student;
import com.algebra.demodatasources.service.LessonService;
import com.algebra.demodatasources.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author al
 * @date 2020/2/12 15:41
 * @description
 */
@RestController
public class TestController {

    @Autowired
    LessonService lessonService;

    @Autowired
    StudentService studentService;

    @GetMapping("/getLessonInfo")
    public WebApiResult<Lesson> getLessonInfo(@RequestParam("id") String lessonId){
        WebApiResult<Lesson> result = new WebApiResult<>();
        Lesson lesson = lessonService.selectByPrimaryKey(lessonId);
        result.setData(lesson);
        result.setMessage("获取成功！");
        return result;
    }

    @GetMapping("/getStudentInfo")
    public WebApiResult<Student> getStudentInfo(@RequestParam("id") String studentId){
        WebApiResult<Student> result = new WebApiResult<>();
        Student student = studentService.selectByPrimaryKey(studentId);
        result.setData(student);
        result.setMessage("获取成功！");
        return result;
    }

}
