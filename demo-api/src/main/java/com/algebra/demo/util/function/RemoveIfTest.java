package com.algebra.demo.util.function;

import com.algebra.demo.dto.PersonDto;
import com.algebra.demo.dto.UserDto;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author al
 * @date 2020/2/27 13:21
 * @description
 */
public class RemoveIfTest {

    public static List<PersonDto> buildData(){
        List<PersonDto> userDtos = new ArrayList<>();
        PersonDto personDto1 = PersonDto.builder().name("tom").password("asdasd").personAge(11).build();
        PersonDto personDto2 = PersonDto.builder().name("孔子").password("dsadsa").personAge(11).build();
        PersonDto personDto3 = PersonDto.builder().name("lily").password("asdasd").personAge(12).build();
        Collections.addAll(userDtos,personDto1,personDto2,personDto3);
        return userDtos;
    }

    public static void test1(){
        List<PersonDto> personDtos = buildData();

        // 写法一
        personDtos.removeIf(new Predicate<PersonDto>() {
            @Override
            public boolean test(PersonDto personDto) {
                return "asdasd".equals(personDto.getPassword());
            }
        });

        // 写法二
//        personDtos.removeIf(personDto -> "asdasd".equals(personDto.getPassword()));

        System.out.println(JSONObject.toJSONString(personDtos));

    }


    public static void main(String[] args) {
        test1();

    }


}
