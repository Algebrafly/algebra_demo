package com.algebra.demo.study.isp;

/**
 * @author al
 * @date 2020/6/8 15:10
 * @description 单继承改为多继承，接口按照功能进行分离
 */
public class PrettyGirl implements IGreatTemperamentGirl, IGoodBodyGirl {

    private String name;

    public PrettyGirl(){}

    public PrettyGirl(String name){
        this.name = name;
    }

    @Override
    public void goodLooking() {
        System.out.println(this.name + "---looking nice!");
    }

    @Override
    public void niceFigure() {
        System.out.println(this.name + "---figure nice!");
    }

    @Override
    public void greatTemperament() {
        System.out.println(this.name + "---temperament nice!");
    }
}
