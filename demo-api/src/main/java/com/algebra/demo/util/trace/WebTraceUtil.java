package com.algebra.demo.util.trace;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * @author al
 * @date 2020/2/10 16:36
 * @description
 */
public class WebTraceUtil {

    /**
     * traceId
     */
    public static final String TRACE_ID= "traceId";

    /**
     * baseInfo
     */
    public static final String BASTINFO = "baseInfo";

    /**
     * clientId
     */
    public static final String CLIENTID = "clientId";

    /**
     * 当traceId为空时，显示的traceId
     */
    private static final String DEFAULT_TRACE_ID = "0";

    /**
     * 设置TraceId，如果参数为空，则设置成默认值
     * @param traceId 参数
     */
    public static void setTraceId(String traceId) {
        traceId = StringUtils.isBlank(traceId) ? DEFAULT_TRACE_ID : traceId;
        MDC.put(TRACE_ID, traceId);
    }

    /**
     * 获取traceId，如果为空则为默认值
     * @return traceId
     */
    public static String getTraceId() {
        String traceId = MDC.get(TRACE_ID);
        return StringUtils.isBlank(traceId) ? DEFAULT_TRACE_ID : traceId;
    }

    /**
     * 判断traceId为默认值
     * @param traceId traceId
     * @return 是否
     */
    public static Boolean defaultTraceId(String traceId) {
        return DEFAULT_TRACE_ID.equals(traceId);
    }

    /**
     * 生成traceId
     * @return traceId
     */
    public static String genTraceId() {
        return UUID.randomUUID().toString();
    }

}
