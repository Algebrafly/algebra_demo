package com.algebra.demo.conf.mq;

import org.springframework.cloud.stream.binder.PartitionKeyExtractorStrategy;
import org.springframework.messaging.Message;

import java.util.Random;

/**
 * @author al
 * @date 2020/3/3 17:16
 * @description
 */
public class MyPartitionKeyStrategy implements PartitionKeyExtractorStrategy {
    @Override
    public Object extractKey(Message<?> message) {
        Object payload = message.getPayload();
        Random random =new Random();

        final int r = random.nextInt(2);

        System.out.println("r:" + r);

        return r;
    }
}
