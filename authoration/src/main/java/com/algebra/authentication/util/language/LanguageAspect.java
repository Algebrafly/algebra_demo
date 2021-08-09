package com.algebra.authentication.util.language;

import com.algebra.authentication.service.config.SysDictionaryService;
import com.algebra.authentication.util.WebApiResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author al
 * @date 2020/7/14 11:41
 * @description LanguageInterceptor
 */
//@Component
//@Aspect
@Slf4j
public class LanguageAspect {

    private String languageType;

    @Autowired
    private SysDictionaryService dictionaryService;

//    @Pointcut("execution(public * com.algebra.authentication.web..*.*(..))")
    public void webResult(){}


//    @Before("webResult()")
    public void doBefore(JoinPoint joinPoint){
        log.info("Controller-before pointcut the method...");

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes != null){
            HttpServletRequest request = attributes.getRequest();
            String languageHeader = request.getHeader("lang");
            if(languageHeader == null){
                languageHeader = request.getParameter("lang");
            }
            languageType = LanguageTypeEnum.getTypeByName(languageHeader);
        } else {
            // default english
            languageType = LanguageTypeEnum.ENGLISH.getType();
        }
        log.info("API language type is {}", languageType);
    }

//    @AfterReturning(returning = "ret", pointcut = "webResult()")
    public void doAfterReturning(Object ret) throws Throwable {
        log.info("Controller-after returning method result! language is {}", languageType);
        if(ret instanceof WebApiResult){
            WebApiResult result = (WebApiResult)ret;
            int code = result.getCode();

            // 根据code查询选择返回提示信息(不支持动态扩展，需要完善)
            String codeMessage = dictionaryService.getReturnMsg(String.valueOf(code),languageType);
            if(codeMessage == null){
                return;
            }
            StringBuilder sb = new StringBuilder(codeMessage);

            String resultMessage = result.getMessage();
            if(resultMessage != null && !"".equals(resultMessage)){
                GoogleTranslate g = GoogleTranslate.getInstance();
                String translateText = g.translateText(resultMessage, "auto", LanguageTypeEnum.getCodeByType(languageType));
                sb.append(": ").append(translateText);
            }
            result.setMessage(sb.toString());
        }

    }

}
