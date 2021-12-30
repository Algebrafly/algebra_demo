package com.algebra.demo.config.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * @apiNote
 * @since 2021/2/7 17:53
 * @author Duxuewei
 * @version v1.0.0
 */
public abstract class AssertLog extends Assert {

    public static void isTrue(boolean expression, String message) {
        Logger log = LoggerFactory.getLogger("AssertLog");
        if (!expression) {
            log.error(message);
        }
        Assert.isTrue(expression,message);
    }

}
