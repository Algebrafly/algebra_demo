package com.algebra.authentication.util.userlog.all;


import com.algebra.authentication.util.userlog.LogAble;
import com.algebra.authentication.vo.UserInfoDto;
import com.algebra.authentication.web.EmailController;
import com.algebra.authentication.web.rbac.UserController;

/**
 * @author zouooh<wanglong @ zhongruigroup.com>
 * @date 2020/8/21 11:01
 * @Desc
 */
public class UserOprLog extends LogAble {
    @Override
    public String menuResId() {
        return CUSTOMER;
    }

    @Override
    public void init() {
        items.put("addUser", "add");
        items.put("deleteUser", "delete");
        items.put("modifyUser", "modify");
    }

    @Override
    public Class<?> targetClazz() {
        return UserController.class;
    }

    @Override
    public String process(String url, Object model) {
        String object = "";
        if (model instanceof UserInfoDto) {
            object = target((UserInfoDto) model);
        }
        if (model instanceof String) {
            object = String.valueOf(model);
        }
        String format = items.get(url);
        if (format == null) {
            return null;
        }
        return format + "," + object;
    }

    private String target(UserInfoDto dto) {
        if (dto.getUsrId() == null) {
            return dto.getUsername();
        }
        return dto.getUsrId() + "#" + dto.getUsername();
    }
}
