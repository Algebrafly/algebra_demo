package com.algebra.authentication.web;

import cn.hutool.json.JSONUtil;
import com.algebra.authentication.domain.SysPermission;
import com.algebra.authentication.domain.SysRolePermission;
import com.algebra.authentication.service.SysPermissionService;
import com.algebra.authentication.service.SysRolePermissionService;
import com.algebra.authentication.util.CommonUtil;
import com.algebra.authentication.util.WebApiResult;
import com.algebra.authentication.vo.RoleMenuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author al
 * @date 2020/7/9 10:52
 * @description 角色菜单
 */
@Slf4j
@RestController
@Api(value = "Menu", tags = "角色菜单")
public class MenuController {

    @Autowired
    SysPermissionService permissionService;

    @Autowired
    SysRolePermissionService rolePermissionService;


    @ApiOperation("添加菜单")
    @PostMapping("addPermission")
    public WebApiResult<String> addPermission(@RequestBody SysPermission permission) {
        log.info("添加菜单，请求参数：{}", JSONUtil.toJsonStr(permission));
        try {
            permission.setCreateTime(new Date());
            permission.setPermId(CommonUtil.createPrimaryKey("perm"));
            boolean save = permissionService.save(permission);

        } catch (Exception e) {
            log.error("添加菜单异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
        return WebApiResult.ok("添加菜单成功！");
    }

    @ApiOperation("修改菜单")
    @PostMapping("modifyPermission")
    public WebApiResult<String> modifyPermission(@RequestBody SysPermission permission) {
        log.info("修改菜单，请求参数：{}", JSONUtil.toJsonStr(permission));
        try {

            boolean b = permissionService.updateById(permission);

        } catch (Exception e) {
            log.error("修改菜单异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
        return WebApiResult.ok("修改菜单成功！");
    }

    @ApiOperation("删除菜单")
    @GetMapping("deletePermission")
    public WebApiResult<String> deletePermission(@RequestParam("permissionId") String permissionId) {
        log.info("删除菜单，请求参数：{}", permissionId);
        try {

            boolean b = permissionService.removeById(permissionId);

        } catch (Exception e) {
            log.error("删除菜单异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
        return WebApiResult.ok("删除菜单成功！");
    }

    @ApiOperation("查询菜单详情")
    @GetMapping("getOnePermission")
    public WebApiResult<SysPermission> getOnePermission(@RequestParam("permissionId") String permissionId) {
        log.info("查询菜单详情，请求参数：{}", permissionId);
        try {
            SysPermission permission = permissionService.getById(permissionId);
            return WebApiResult.ok(permission);
        } catch (Exception e) {
            log.error("查询菜单详情异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
    }

    @ApiOperation("查询菜单列表（分级）")
    @GetMapping("getPermissionList")
    public WebApiResult<List<SysPermission>> getPermissionList(@RequestParam(value = "roleId", required = false) String roleId) {
        log.info("查询菜单列表，请求参数：{}", roleId);
        try {
            List<SysPermission> menus = null;
            if (roleId == null || "".equals(roleId)) {
                log.info("查询全部菜单信息！");
                menus = permissionService.getAllMenus();
            } else {
                log.info("查询指定角色下的菜单信息！");
                menus = permissionService.getMenusByRole(roleId);
            }
            return WebApiResult.ok(menus);
        } catch (Exception e) {
            log.error("查询菜单列表异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
    }

    @ApiOperation("关联角色和菜单")
    @PostMapping("linkRoleMenu")
    public WebApiResult<String> linkRoleMenu(@RequestBody RoleMenuVo roleMenuVo) {
        log.info("关联角色和菜单，请求信息：{}", JSONUtil.toJsonStr(roleMenuVo));
        try {
            List<String> menuIds = roleMenuVo.getMenuIds();
            if (menuIds != null && menuIds.size() > 0) {
                List<SysRolePermission> rolePermissions = new ArrayList<>();
                for (String menuId : menuIds) {
                    SysRolePermission sysRolePermission = new SysRolePermission();
                    sysRolePermission.setCreateTime(new Date());
                    sysRolePermission.setPermId(menuId);
                    sysRolePermission.setRoleId(roleMenuVo.getRoleId());
                    rolePermissions.add(sysRolePermission);
                }
                rolePermissionService.saveBatch(rolePermissions);
            }
        } catch (Exception e) {
            log.error("关联角色和菜单异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
        return WebApiResult.ok();
    }


}
