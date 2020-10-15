package com.algebra.authentication.util;

import com.algebra.authentication.domain.SysUser;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author al
 */
@Service
public class UserUtil {

    public SysUser getCurrentUserInfo() {
        return this.getCurrentUserInfo(null);
    }

    public SysUser getCurrentUserInfo(HttpServletRequest request) {
        String userInfo = "";
        if (request == null) {
            userInfo = MDC.get(MdcConstant.USER_INFO);
        } else {
            userInfo = String.valueOf(request.getAttribute("userInfo"));
        }

        try {
            return JSONObject.parseObject(userInfo, SysUser.class);
        } catch (Exception ex) {
            return null;
        }
    }
}

