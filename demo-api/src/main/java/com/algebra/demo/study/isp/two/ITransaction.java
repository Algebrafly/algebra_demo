package com.algebra.demo.study.isp.two;

/**
 * @author al
 * @date 2020/6/8 15:35
 * @description 操作
 */
public abstract class ITransaction {

    /**
     * 实际操作（存款、转账、退出等）
     */
    abstract void execute();

}
