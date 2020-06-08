package com.algebra.demo.study.isp;

/**
 * @author al
 * @date 2020/6/8 15:14
 * @description
 */
public abstract class AbstractSearcher {

    protected PrettyGirl prettyGirl;

    public AbstractSearcher(){}

    public AbstractSearcher(PrettyGirl prettyGirl){
        this.prettyGirl = prettyGirl;
    }

    public abstract void show();

}
