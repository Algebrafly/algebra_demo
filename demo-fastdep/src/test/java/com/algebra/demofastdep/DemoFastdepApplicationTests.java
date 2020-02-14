package com.algebra.demofastdep;

import com.algebra.demofastdep.entity.domain.Student;
import com.algebra.demofastdep.mapper.slave.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoFastdepApplicationTests {

    @Autowired
    StudentMapper studentMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void mapperTest(){
        Map<String,Object> param = new HashMap<>();
        List<Student> students = studentMapper.selectList(param);
    }

}
