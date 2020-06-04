package com.algebra.demo.study.lsp;

/**
 * @author al
 * @date 2020/6/4 16:17
 * @description Liskov替换原则 基于这样的一个事实，那就是客户程序在调用某一个类时，实际上是对该类的整个继承体系设定了一个契约，
 * 继承体系中的所有类必须遵循这一契约，即前置条件和 后置条件必须保持一致 。
 * 这就超越了继承中子类与父类之间形成的is-a关系，为对象继承加上了一把严格的枷锁。显然，Liskov替换原则对于约束继承的泛滥具有重要意义。
 *
 * 我个人认为，Liskov替换原则并不是要求子类不能新增父类没有的方法或者属性。因为从调用父类的客户程序的角度来说，它关心的仅仅是父类的行 为，
 * 只要子类对于父类的行为是可替换的，就不算是违背该原则。恰恰相反，当你发现父类拥有子类不希望继承，或者勉强继承会对子类造成破坏时 ，
 * 正可以说明这个继承体系可能存在问题，违背了Liskov替换原则。这就充分说明，子类并不关心父类的行为，但却需要遵循父类制定的规范或契约，
 * 以满足 客户调用父类的期望。正所谓"萧规曹随"，如果前人制定的规范我们不遵循，反而要去打破，那就不是继承，而是铁了心要另起炉灶了。
 */
public abstract class LinerObject {

    private double slope;

    private double intercept;

    public double getIntercept() {
        return intercept;
    }

    public double getSlope() {
        return slope;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public void setIntercept(double intercept) {
        this.intercept = intercept;
    }

    /**
     * 判断一个点是否在直线上
     * @param point 点对象
     * @return boolean
     */
    abstract boolean isOn(Point point);


}
