package com.algebra.authentication.util.userlog;

import com.algebra.authentication.domain.SysUser;
import com.algebra.authentication.domain.SysUserLog;
import com.algebra.authentication.service.config.SysUserLogService;
import com.algebra.authentication.util.UserUtil;
import com.algebra.authentication.util.WebApiResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @author al
 * @date 2020/7/24 13:59
 * @description 用户操作日志(匹配指定接口)
 */
@Component
@Aspect
@Slf4j
public class UserOprLogAspect {

    @Autowired
    SysUserLogService userLogService;
    @Autowired
    UserUtil userUtil;
    @Autowired
    LogHelper logHelper;

    @Pointcut("@annotation(com.algebra.authentication.util.userlog.SysLogAsp)")
    public void userOpr() {}


    @AfterReturning(returning = "ret", pointcut = "userOpr()")
    public void doAfterReturning(JoinPoint joinPoint, Object ret) {
        try {
            doLog(joinPoint, ret);
        } catch (Exception e) {
            log.error("sys log failed: {}", e.getMessage());
        }
    }

    private void doLog(JoinPoint joinPoint, Object ret) throws Exception {
        // 操作完成，记录入Log库
        if (!(ret instanceof WebApiResult)) {
            return;
        }
        if (!((WebApiResult<?>) ret).getSuccess()) {
            return;
        }
        Class<?> targetClazz = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        Object arg =  joinPoint.getArgs()[0];;

        String logContent = logHelper.logContent(targetClazz, methodName, arg);
        if (logContent != null) {
            log.info(" it have been logged that {} {}", targetClazz, methodName);
            SysUserLog sysLog = new SysUserLog();
            SysUser currentUser = userUtil.getCurrentUserInfo();
            if (currentUser != null) {
                sysLog.setUserId(currentUser.getUsrId());
                sysLog.setUserName(currentUser.getUsername());
                sysLog.setCreatedAt(new Date());
                sysLog.setCreatedAt(new Date());
                sysLog.setDeptId(currentUser.getUsrOrganization());
            }
            sysLog.setContent(logContent);
            sysLog.setMenu(logHelper.menu(targetClazz));
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                sysLog.setIp(getIpAddr(attributes.getRequest()));
            }
            userLogService.save(sysLog);
        } else {
            log.info(" there is not any config for {} {}", targetClazz, methodName);
        }
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = "";
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if ("127.0.0.1".equals(ipAddress)) {
                    // 根据网卡取本机配置的IP
                    try {
                        ipAddress = InetAddress.getLocalHost().getHostAddress();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                }
            }
            // 通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null) {
                if (ipAddress.contains(",")) {
                    return ipAddress.split(",")[0];
                } else {
                    return ipAddress;
                }
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
