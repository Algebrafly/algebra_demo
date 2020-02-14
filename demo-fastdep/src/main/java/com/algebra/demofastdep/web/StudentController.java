package com.algebra.demofastdep.web;

import com.algebra.demofastdep.conf.web.WebApiResult;
import com.algebra.demofastdep.entity.domain.Student;
import com.algebra.demofastdep.service.StudentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author al
 * @date 2020/2/12 15:41
 * @description
 */
@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/getStudentInfo")
    public WebApiResult<Student> getStudentInfo(@RequestParam("id") String studentId){
        WebApiResult<Student> result = new WebApiResult<>();
        Student student = studentService.selectByPrimaryKey(studentId);
        result.setData(student);
        result.setMessage("获取成功！");
        return result;
    }

    @GetMapping("/getStudentList")
    public WebApiResult<List<Student>> getStudentList(){
        WebApiResult<List<Student>> result = new WebApiResult<>();
        Map<String,Object> param = new HashMap<>();
        PageInfo<Student> students = studentService.getStudents(param);

        result.setCount(students.getSize());
        result.setData(students.getList());
        result.setMessage("获取成功！");
        return result;
    }

}
