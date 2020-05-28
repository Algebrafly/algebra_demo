package com.algebra.demo.study.coursetemp;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author al
 * @date 2020/5/28 9:13
 * @description
 */
public class TestPartList {


    public static List<String> initSrcList(){
        List<String> al = new ArrayList<>();
        Collections.addAll(al,"01","12","23","34","45","56","67","78","89","90","aa","bb","cc","dd","ee","ff","ee","ff","hh");
        return al;
    }

    public static void main(String[] args) {

        List<String> srcList = initSrcList();
        List<List<String>> partition = Lists.partition(srcList, 5);

        for (List<String> list : partition) {

            System.out.println(list.toString());

        }


    }

}
