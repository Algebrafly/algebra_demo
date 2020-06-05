package com.algebra.demo.study.dip;

/**
 * @author al
 * @date 2020/6/5 11:42
 * @description 按钮
 */
public class Button {

    /**
     * 模拟运行时间（实际应该采用计数器来做）
     */
    private static final Long RUNNING_TIME = 1000L;

    /**
     * 按钮按下操作：超时关闭操作
     * 【解除了按钮类和机器类（台灯，发动机...）之间的耦合关系】
     * @param switchableDevice 按钮连接的对象
     * @param timing 定时时间
     */
    public void poll(SwitchableDevice switchableDevice, TimeSchedule timeSchedule, Long timing){
        if(timeSchedule.isTimeOut(timing)){
            switchableDevice.turnOff();
        } else {
//            switchableDevice.turnOn();
        }
    }


    public static void main(String[] args) {

        SwitchableDevice device = new Lamp("lamp-A");

        // lamp-B已经打开了1000ms，超出定时时间 999ms
        Lamp lamp = new Lamp("lamp-B");
        lamp.setRunningTime(RUNNING_TIME);
        lamp.setOn(true);

//        SwitchableDevice device2 = new Lamp("motor-A");

        // 定时 999ms
        new Button().poll(lamp,lamp,999L);


    }


}
