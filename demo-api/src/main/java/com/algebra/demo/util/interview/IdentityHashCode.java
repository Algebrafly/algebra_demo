package com.algebra.demo.util.interview;

import com.algebra.demo.dto.PersonDto;

/**
 * @author al
 * @date 2020/11/5 14:12
 * @description 测试对象引用和对象clone
 * 浅拷贝：被复制对象的所有值属性都含有与原来对象的相同，而所有的对象引用属性仍然指向原来的对象。
 * 深拷贝：在浅拷贝的基础上，所有引用其他对象的变量也进行了clone，并指向被复制过的新对象。
 */
public class IdentityHashCode {

    public static void test(PersonDto dto) {

        PersonDto p2 = dto;
        p2.setName("tom");
        p2.setPassword("666666");
        System.out.println(System.identityHashCode(p2));
        System.out.println(p2.toString());
    }

    public static void testClone(PersonDto dto) throws CloneNotSupportedException {

        PersonDto p2 = (PersonDto) dto.clone();
        p2.setName("tom");
        p2.setPassword("888888");
        System.out.println(System.identityHashCode(p2));
        System.out.println(p2.toString());
    }


    public static void main(String[] args) throws CloneNotSupportedException {
        PersonDto dto = PersonDto.builder().name("tom").password("123456").build();
        System.out.println(System.identityHashCode(dto));
        System.out.println(dto.toString());
        test(dto);
        testClone(dto);

    }


}
