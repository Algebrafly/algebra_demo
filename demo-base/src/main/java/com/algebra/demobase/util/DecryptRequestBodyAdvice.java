package com.algebra.demobase.util;

import com.algebra.demo.util.AESUtil;
import com.sun.deploy.config.DefaultConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * @auther: lgq
 * @time: 2019/10/29 13:05
 * @description:
 */
@Component
@Service
@ControllerAdvice(basePackages = "com.lunz.fin.controller")
@Slf4j
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        return new HttpInputMessage() {

            @Override
            public InputStream getBody() throws IOException {
                InputStream inputStream = inputMessage.getBody();
                DefaultConfig encryptEnabled = new DefaultConfig();
                //asdhfjkas  就是 0  不加密，  lkjdfklgjsfd 就是 1  加密
                if(!"asdhfjkas".equals(encryptEnabled.getProperty("asd"))){
                    log.info("开启数据传输解密-------处理body");
                    ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len = -1;
                    while ((len = inputStream.read(buffer)) != -1) {
                        outSteam.write(buffer, 0, len);
                    }
                    //为了处理中文乱码问题
                    outSteam.close();
                    inputStream.close();
                    byte[] lens = outSteam.toByteArray();
                    String bodyStr = new String(lens,"utf-8");
                    log.info("取出body:{}",bodyStr);

                    String dealData = null;
                    //去除空格和特殊符号
                    bodyStr.trim();
                    bodyStr = bodyStr.replaceAll("\r|\n|\t", "");
                    dealData = AESUtil.decrypt(bodyStr);
                    log.info("数据解密,加密的数据:{},解密的数据:{}",bodyStr,dealData);
                    if(dealData == null){
                        dealData = bodyStr;
                    }
                    return new ByteArrayInputStream(dealData.getBytes("utf-8"));

                }else{
                    return inputStream;
                }
            }

            @Override
            public HttpHeaders getHeaders() {
                return inputMessage.getHeaders();
            }
        };
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(@Nullable Object var1, HttpInputMessage var2, MethodParameter var3, Type var4, Class<? extends HttpMessageConverter<?>> var5) {
        return var1;
    }

}
