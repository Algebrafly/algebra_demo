package com.algebra.authentication.util.userlog.all;


import com.algebra.authentication.util.userlog.LogAble;
import com.algebra.authentication.web.LoginController;

/**
 * @author zal
 * @date 2020/8/21 9:52
 * @Desc
 */
public class HomeLog extends LogAble {
    @Override
    public String menuResId() {
        return HOME;
    }

    @Override
    public void init() {
        items.put("login", "login");
        items.put("logout", "logout");
    }

    @Override
    public Class<?> targetClazz() {
        return LoginController.class;
    }

    @Override
    public String process(String url, Object model) {
        return items.get(url);
    }
}
