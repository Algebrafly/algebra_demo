package com.algebra.authentication.web;

import cn.hutool.json.JSONUtil;
import com.algebra.authentication.domain.SysUser;
import com.algebra.authentication.service.SysUserService;
import com.algebra.authentication.util.CommonUtil;
import com.algebra.authentication.util.WebApiResult;
import com.algebra.authentication.util.struct.UserInfoConvert;
import com.algebra.authentication.vo.UserInfoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author al
 * @date 2020/7/7 16:02
 * @description 用户-角色-权限-菜单
 */
@Slf4j
@RestController
@Api(value = "User",tags = "用户角色权限菜单")
public class UserController {

    @Autowired
    UserInfoConvert userInfoConvert;

    @Autowired
    SysUserService userService;

    // ----------------基本操作-------------
    @ApiOperation("添加用户")
    @PostMapping("addUser")
    public WebApiResult<String> addUser(@RequestBody UserInfoDto userInfoDto) {
        log.info("添加用户，接收到请求参数:{}", JSONUtil.toJsonStr(userInfoDto));
        try {
            SysUser sysUser = userInfoConvert.userDtoToDo(userInfoDto);
            sysUser.setCreateTime(new Date());
            sysUser.setUsrId(CommonUtil.createPrimaryKey("user"));
            boolean save = userService.save(sysUser);

        } catch (Exception e) {
            log.error("添加用户异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
        return WebApiResult.ok("添加用户成功！");
    }


    // ----------------列表操作-------------



    // ----------------关联操作-------------



}
