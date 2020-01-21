package com.algebra.demobase.util;

import com.algebra.demo.util.AESUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.deploy.config.DefaultConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther: lgq
 * @time: 2019/10/29 13:12
 * @description:
 */
@Component
@ControllerAdvice(basePackages = "com.lunz.fin.controller")
@Slf4j
public class EncryResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object object, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        //通过 ServerHttpRequest的实现类ServletServerHttpRequest 获得HttpServletRequest
        ServletServerHttpRequest sshr = (ServletServerHttpRequest) serverHttpRequest;
        //此处获取到request 是为了取到在拦截器里面设置的一个对象 是我项目需要,可以忽略
        HttpServletRequest request = sshr.getServletRequest();

        String returnStr = "";

        Object obj = null;

        try {

            DefaultConfig encryptEnabled = new DefaultConfig();
            //asdhfjkas  就是 0  不加密，  lkjdfklgjsfd 就是 1  加密
            if(!"asdhfjkas".equals(encryptEnabled.getProperty("aaa"))){
                log.info("开启数据传输加密");
                if("GetConfigByKey".equals(returnType.getMethod().getName())){
                    //这个接口不加密
                    log.info("GetConfigByKey这个接口不加密");
                    obj = object;
                }else{
                    //添加encry header，告诉前端数据已加密
                    serverHttpResponse.getHeaders().add("encry", "true");
                    //SerializerFeature.WriteMapNullValue null值也有
                    //SerializerFeature.DisableCircularReferenceDetect 不检测重复引用
                    String srcData = JSON.toJSONString(object, SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect);
                    //加密
                    returnStr = AESUtil.encrypt(srcData);
                    log.info("接口={},原始数据={},加密后数据={}", request.getRequestURI(), srcData, returnStr);
                    obj = returnStr;
//                obj = object;
                }
            }else{
                obj = object;
            }
            return obj;
        } catch (Exception e) {
            log.error("加密异常！", e);
        }
        return object;
    }
}
