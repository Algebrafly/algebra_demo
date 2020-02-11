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
    public static final String MY_TRACE_ID = "myTraceId";

    /**
     * baseInfo
     */
    public static final String BASE_INFO = "baseInfo";

    /**
     * clientId
     */
    public static final String CLIENT_ID = "clientId";

    /**
     * 当traceId为空时，显示的traceId
     */
    private static final String DEFAULT_TRACE_ID = "0";

    /**
     * 设置TraceId，如果参数为空，则设置成默认值
     * @param myTraceId 参数
     */
    public static void setMyTraceId(String myTraceId) {
        myTraceId = StringUtils.isBlank(myTraceId) ? DEFAULT_TRACE_ID : myTraceId;
        MDC.put(MY_TRACE_ID, myTraceId);
    }

    /**
     * 获取traceId，如果为空则为默认值
     * @return traceId
     */
    public static String getMyTraceId() {
        String traceId = MDC.get(MY_TRACE_ID);
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
    public static String getTraceId() {
        return UUID.randomUUID().toString();
    }


    /**
     * 当ClientId为空时，显示的ClientId
     */
    private static final String DEFAULT_CLIENT_ID = "0";

    /**
     * 设置ClientId，如果参数为空，则设置成默认值
     * @param clientId 参数
     */
    public static void setClientId(String clientId) {
        clientId = StringUtils.isBlank(clientId) ? DEFAULT_TRACE_ID : clientId;
        MDC.put(CLIENT_ID, clientId);
    }

    /**
     * clientId，如果为空则为默认值
     * @return clientId
     */
    public static String getClientId() {
        String clientId = MDC.get(CLIENT_ID);
        return StringUtils.isBlank(clientId) ? DEFAULT_CLIENT_ID : clientId;
    }

    /**
     * clientId
     * @param clientId clientId
     * @return 是否
     */
    public static Boolean defaultClientId(String clientId) {
        return DEFAULT_CLIENT_ID.equals(clientId);
    }

    /**
     * 生成clientId
     * @return clientId
     */
    public static String initClientId() {
        return UUID.randomUUID().toString();
    }

}
