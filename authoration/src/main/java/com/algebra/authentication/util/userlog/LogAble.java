package com.algebra.authentication.util.userlog;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zouooh<wanglong @ zhongruigroup.com>
 * @date 2020/8/21 9:28
 * @Desc
 */
public abstract class LogAble implements Menu{

    /**
     * i18n资源id
     */
    public abstract String menuResId();

    public abstract void init();

    public abstract Class<?> targetClazz();

    public abstract String process(String url,Object model);

    protected Map<String,String> items = new HashMap<>();
}
