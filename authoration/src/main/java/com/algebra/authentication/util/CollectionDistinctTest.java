package com.algebra.authentication.util;

import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @author al
 * @date 2020/10/12 9:34
 * @description
 */
public class CollectionDistinctTest {


    public static void  listDistinctForString(){
        List<String> al = new ArrayList<String>(){{
            add("a");
            add("a");
            add("c");
            add("b");
            add("c");
        }};
        System.out.println("去重前："+ JSONUtil.toJsonStr(al));
        List<String> collect = al.stream().distinct().collect(Collectors.toList());
        System.out.println("去重后："+ JSONUtil.toJsonStr(collect));

    }

    public static void listDistinctForObject(){
        Student s1 = new Student("tom","1001");
        Student s2 = new Student("tom","1002");
        Student s3 = new Student("lily","1003");
        Student s4 = new Student("tom","1001");
        List<Student> al = new ArrayList<Student>(){{
            add(s1);
            add(s2);
            add(s3);
            add(s4);
        }};
        System.out.println("去重前："+ JSONUtil.toJsonStr(al));
        List<Student> collect = al.stream().distinct().collect(Collectors.toList());
        System.out.println("Distinct去重后："+ JSONUtil.toJsonStr(collect));
        ArrayList<Student> students = al.stream().collect(
                collectingAndThen(
                        toCollection(() -> new TreeSet<>(Comparator.comparing(Student::getName))), ArrayList::new)
        );
        System.out.println("Prop去重后："+ JSONUtil.toJsonStr(students));
    }


    public static void listDistinctForObjectByFilter(){
        Student s1 = new Student("tom","1001");
        Student s2 = new Student("tom","1002");
        Student s3 = new Student("lily","1003");
        Student s4 = new Student("tom","1001");
        List<Student> al = new ArrayList<Student>(){{
            add(s1);
            add(s2);
            add(s3);
            add(s4);
        }};
        System.out.println("去重前："+ JSONUtil.toJsonStr(al));
        List<Student> collect = al.stream().filter(CommonUtil.distinctByKey(Student::getName)).collect(Collectors.toList());
        System.out.println("去重后："+ JSONUtil.toJsonStr(collect));
    }


    public static void main(String[] args) {

        listDistinctForObjectByFilter();

    }



}

@Data
@AllArgsConstructor
class Student{
    private String name;
    private String idNo;
}
