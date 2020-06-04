package com.algebra.demo.study.lsp;

/**
 * @author al
 * @date 2020/6/4 14:15
 * @description 子类会继承父类的所有东西，而修饰符只是影响属性或者方法对外是否可见
 * [官方] 子类不能继承父类的私有属性，但是如果子类中公有的方法影响到了父类私有属性，那么私有属性是能够被子类使用的。
 */
public class Square extends Rectangle {


    /**
     * IS-A 约束的是一种行为
     * 在重新生命派生类中的例程时，只能使用相等或者更弱的前置条件来替换原始的前置条件。
     * 只能使用相等或者更强的后置条件来替换原始的后置条件【？】
     * @param width
     */
    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        super.setWidth(height);
    }
}
