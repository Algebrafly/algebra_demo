package com.algebra.demo.study.isp;

/**
 * @author al
 * @date 2020/6/8 15:16
 * @description
 */
public class Searcher extends AbstractSearcher {

    public Searcher(){}

    public Searcher(PrettyGirl prettyGirl){
        super(prettyGirl);
    }

    @Override
    public void show() {
        System.out.println("--------- info as following---------------");
        //展示面容
        super.prettyGirl.goodLooking();
        //展示身材
        super.prettyGirl.niceFigure();
        //展示气质
        super.prettyGirl.greatTemperament();
    }





}
