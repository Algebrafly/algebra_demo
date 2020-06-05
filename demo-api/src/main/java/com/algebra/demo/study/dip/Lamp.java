package com.algebra.demo.study.dip;

/**
 * @author al
 * @date 2020/6/5 11:43
 * @description 台灯
 */
public class Lamp extends Machine implements SwitchableDevice, TimeSchedule {

    private boolean isOn;

    private String name;

    private Long runningTime;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public void setRunningTime(Long runningTime) {
        this.runningTime = runningTime;
    }

    public Long getRunningTime() {
        return runningTime;
    }

    public Lamp(){}

    public Lamp(String name){
        this.name = name;
    }

    @Override
    public void turnOn() {
        System.out.println(this.name+"打开！");
        this.isOn = true;
    }

    @Override
    public void turnOff() {
        System.out.println(this.name+"关闭！");
        this.isOn = false;
    }

    @Override
    public boolean isTimeOut(Long timing) {
        return this.isOn && this.runningTime.compareTo(timing) > 0;
    }
}
