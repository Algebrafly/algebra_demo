package com.algebra.demobase.conf.redis;

import java.util.concurrent.TimeUnit;

/**
 * @author al
 * @date 2019/12/6 13:23
 * @description
 */
public class Status {

    enum ExpireEnum {

        EFFECT_TIME_1(1000L,TimeUnit.SECONDS),
        EFFECT_TIME_2(2000L,TimeUnit.SECONDS),
        ;
        ExpireEnum(long time, TimeUnit timeUnit){
            this.time = time;
            this.timeUnit = timeUnit;
        }
        public long getTime(){
            return EFFECT_TIME_1.time;
        };
        public TimeUnit getTimeUnit(){
            return EFFECT_TIME_1.timeUnit;
        };
        private long time;
        private TimeUnit timeUnit;
    }


}
