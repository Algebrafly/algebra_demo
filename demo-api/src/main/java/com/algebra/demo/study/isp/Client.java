package com.algebra.demo.study.isp;

/**
 * @author al
 * @date 2020/6/8 15:18
 * @description
 */
public class Client {

    //搜索并展示美女信息
    public static void main(String[] args) {
        //定义一个美女
        PrettyGirl xiaoMei = new PrettyGirl("小美");
        AbstractSearcher searcher = new Searcher(xiaoMei);
        searcher.show();
    }

}
