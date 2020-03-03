package com.algebra.demo.util;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author al
 * @date 2020/3/3 11:30
 * @description 自定义通道定义
 */
public interface BaristaIn {

    String MY_INPUT="my_input";

    /**
     * 注入了一个名为my_input的通道
     * 注解 @input声明了它是一个输入类型的通道
     */
    @Input(BaristaIn.MY_INPUT)
    MessageChannel myInput();

}
