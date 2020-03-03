package com.algebra.demo.util;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author al
 * @date 2020/3/3 11:30
 * @description 自定义通道定义
 */
public interface BaristaOut {

    String MY_OUTPUT="my_output";

    /**
     * 注入了一个名为my_output的通道
     * 注解 @Output声明了它是一个输出类型的通道
     */
    @Output(BaristaOut.MY_OUTPUT)
    MessageChannel myOutput();

}
