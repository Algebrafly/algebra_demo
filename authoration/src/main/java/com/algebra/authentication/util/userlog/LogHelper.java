package com.algebra.authentication.util.userlog;

import com.algebra.authentication.domain.SysUserLog;
import com.algebra.authentication.util.userlog.all.HomeLog;
import com.algebra.authentication.util.userlog.all.UserOprLog;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author al
 * @date 2020/8/21 11:13
 * @Desc
 */
@Component
public class LogHelper {
    List<LogAble> logAbles = new ArrayList<>();

    public LogHelper() {
        init();
    }

    private void init() {
        logAbles.add(new HomeLog());
        // ... 以菜单为依据新增要记录log的controller
        logAbles.add(new UserOprLog());

        logAbles.forEach(LogAble::init);
    }

    public String logContent(Class<?> clazz, String methodName, Object model) {
        if (Strings.isBlank(methodName)) {
            return null;
        }
        for (LogAble logAble : logAbles) {
            if (clazz != logAble.targetClazz()) {
                continue;
            }
            String log = logAble.process(methodName, model);
            if (log != null) {
                return log;
            }
        }
        return null;
    }

    public String menu(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        for (LogAble logAble : logAbles) {
            if (clazz == logAble.targetClazz()) {
                return logAble.menuResId();
            }
        }
        return null;
    }
}
