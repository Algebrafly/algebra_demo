package com.algebra.authentication.web;

import cn.hutool.json.JSONUtil;
import com.algebra.authentication.domain.SysRole;
import com.algebra.authentication.service.SysRoleService;
import com.algebra.authentication.util.WebApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author al
 * @date 2020/7/8 16:51
 * @description 角色权限/菜单
 */
@Slf4j
@RestController
@Api(value = "Role",tags = "角色权限/菜单")
public class RoleController {

    @Autowired
    SysRoleService roleService;

    @ApiOperation("添加角色")
    @PostMapping("addRole")
    public WebApiResult<String> addRole(@RequestBody SysRole sysRole){
        log.info("添加角色，请求参数：{}", JSONUtil.toJsonStr(sysRole));
        try {

            sysRole.setCreateTime(new Date());
            boolean save = roleService.save(sysRole);

        } catch (Exception e) {
            log.error("添加角色异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
        return WebApiResult.ok("添加角色成功！");
    }

    @ApiOperation("修改角色")
    @PostMapping("modifyRole")
    public WebApiResult<String> modifyRole(@RequestBody SysRole sysRole){
        log.info("修改角色，请求参数：{}", JSONUtil.toJsonStr(sysRole));
        try {

            sysRole.setUpdateTime(new Date());
            boolean save = roleService.updateById(sysRole);

        } catch (Exception e) {
            log.error("修改角色异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
        return WebApiResult.ok("修改角色成功！");
    }

    @ApiOperation("删除角色")
    @GetMapping("deleteRole")
    public WebApiResult<String> deleteRole(@RequestParam("roleId") String roleId){
        log.info("删除角色，请求参数：{}", roleId);
        try {
            boolean save = roleService.removeById(roleId);
        } catch (Exception e) {
            log.error("删除角色异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
        return WebApiResult.ok("删除角色成功！");
    }





}
