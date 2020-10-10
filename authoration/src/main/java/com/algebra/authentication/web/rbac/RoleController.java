package com.algebra.authentication.web.rbac;

import cn.hutool.json.JSONUtil;
import com.algebra.authentication.domain.SysAuthorization;
import com.algebra.authentication.domain.SysRole;
import com.algebra.authentication.domain.SysRoleAuthorization;
import com.algebra.authentication.service.rbac.SysAuthorizationService;
import com.algebra.authentication.service.rbac.SysRoleAuthorizationService;
import com.algebra.authentication.service.rbac.SysRoleService;
import com.algebra.authentication.util.CommonUtil;
import com.algebra.authentication.util.WebApiResult;
import com.algebra.authentication.vo.RoleAuthorizationVo;
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
 * @date 2020/7/8 16:51
 * @description 角色权限
 */
@Slf4j
@RestController
@Api(value = "Role",tags = "角色权限")
public class RoleController {

    @Autowired
    SysRoleService roleService;

    @Autowired
    SysRoleAuthorizationService roleAuthorizationService;

    @Autowired
    SysAuthorizationService authorizationService;

    @ApiOperation("添加角色")
    @PostMapping("addRole")
    public WebApiResult<String> addRole(@RequestBody SysRole sysRole){
        log.info("添加角色，请求参数：{}", JSONUtil.toJsonStr(sysRole));
        try {
            sysRole.setRoleId(CommonUtil.createPrimaryKey("role"));
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

    @ApiOperation("获取角色列表")
    @GetMapping("getRoleInfoList")
    public WebApiResult<List<SysRole>> getRoleInfoList(@RequestParam(value = "orgId",required = false) String orgId){
        log.info("获取角色列表，请求参数：{}", orgId);
        try {
            List<SysRole> roleInfoList = roleService.getRoleInfoList(orgId);
            return WebApiResult.ok(roleInfoList);
        } catch (Exception e) {
            log.error("获取角色列表异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
    }

    @ApiOperation("角色关联权限")
    @PostMapping("linkRoleAuthorization")
    public WebApiResult<String> linkRoleAuthorization(@RequestBody RoleAuthorizationVo roleAuthorization){
        log.info("角色关联权限，请求信息：{}", JSONUtil.toJsonStr(roleAuthorization));
        try {
            List<String> authIds = roleAuthorization.getAuthorizationList();
            if(authIds != null && authIds.size() > 0){
                log.info("给用户添加角色信息！");
                List<SysRoleAuthorization> sysRoleAuthorizations = new ArrayList<>();
                for (String authId : authIds) {
                    SysRoleAuthorization sysRoleAuthorization = new SysRoleAuthorization();
                    sysRoleAuthorization.setAuthId(authId);
                    sysRoleAuthorization.setRoleId(roleAuthorization.getRoleId());
                    sysRoleAuthorization.setCreateTime(new Date());
                    sysRoleAuthorizations.add(sysRoleAuthorization);
                }
                roleAuthorizationService.saveBatch(sysRoleAuthorizations);
            }
        } catch (Exception e) {
            return WebApiResult.error(e);
        }
        return WebApiResult.ok();
    }


    // ------------------权限配置--------------
    @ApiOperation("获取权限列表")
    @GetMapping("getAuthorizationList")
    public WebApiResult<List<SysAuthorization>> getAuthorizationList(){
        return WebApiResult.ok(authorizationService.getAllAuthorizations());
    }

    @ApiOperation("添加权限")
    @GetMapping("addAuthorization")
    public WebApiResult<String> addAuthorization(@RequestBody SysAuthorization sysAuthorization) {
        log.info("添加权限，接收到参数：{}", JSONUtil.toJsonStr(sysAuthorization));
        try {
            sysAuthorization.setAuthId(CommonUtil.createPrimaryKey("role"));
            sysAuthorization.setCreateAt(new Date());
            boolean save = authorizationService.save(sysAuthorization);

        } catch (Exception e) {
            log.error("添加权限异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
        return WebApiResult.ok("添加权限成功！");

    }


}
