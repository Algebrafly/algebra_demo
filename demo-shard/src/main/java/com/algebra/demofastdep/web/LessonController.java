package com.algebra.demofastdep.web;

import com.algebra.demo.util.base.WebApiResult;
import com.algebra.demofastdep.entity.domain.Lesson;
import com.algebra.demofastdep.service.LessonService;
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
public class LessonController {

    @Autowired
    LessonService lessonService;

    @GetMapping("/getLessonInfo")
    public WebApiResult<Lesson> getLessonInfo(@RequestParam("id") String lessonId){
        WebApiResult<Lesson> result = new WebApiResult<>();
        Lesson lesson = lessonService.selectByPrimaryKey(lessonId);
        result.setData(lesson);
        result.setMessage("获取成功！");
        return result;
    }

}
