package com.algebra.authentication.web;

import cn.hutool.json.JSONUtil;
import com.algebra.authentication.domain.SysUser;
import com.algebra.authentication.service.SysUserService;
import com.algebra.authentication.util.CommonUtil;
import com.algebra.authentication.util.PageRequestParam;
import com.algebra.authentication.util.WebApiResult;
import com.algebra.authentication.util.struct.UserInfoConvert;
import com.algebra.authentication.vo.UserInfoDto;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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

    @ApiOperation("修改用户")
    @PostMapping("modifyUser")
    public WebApiResult<String> modifyUser(@RequestBody UserInfoDto userInfoDto) {
        log.info("修改用户，接收到请求参数:{}", JSONUtil.toJsonStr(userInfoDto));
        try {
            if(userInfoDto.getUsrId() == null){
                return WebApiResult.error("用户参数异常！");
            }
            SysUser sysUser = userInfoConvert.userDtoToDo(userInfoDto);
            boolean save = userService.updateById(sysUser);

        } catch (Exception e) {
            log.error("修改用户异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
        return WebApiResult.ok("修改用户成功！");
    }

    @ApiOperation("删除用户")
    @GetMapping("deleteUser")
    public WebApiResult<String> deleteUser(@RequestParam("usrId") String usrId) {
        log.info("删除用户，接收到请求参数:{}", usrId);
        try {
            if(usrId == null){
                return WebApiResult.error("参数异常！");
            }
            boolean save = userService.removeById(usrId);

        } catch (Exception e) {
            log.error("删除用户异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
        return WebApiResult.ok("删除用户成功！");
    }

    @ApiOperation("获取用户详情")
    @GetMapping("getOneUserInfo")
    public WebApiResult<UserInfoDto> getOneUserInfo(@RequestParam("usrId") String usrId) {
        log.info("获取用户详情，接收到请求参数:{}", usrId);
        UserInfoDto userInfoDto = null;
        try {
            if(usrId == null){
                return WebApiResult.error("参数异常！");
            }
            SysUser sysUser = userService.getById(usrId);
            userInfoDto = userInfoConvert.userDoToDto(sysUser);
            log.info("查询到用户信息：{}", JSONUtil.toJsonStr(userInfoDto));

        } catch (Exception e) {
            log.error("获取用户详情，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
        return WebApiResult.ok(userInfoDto);
    }

    // ----------------列表操作-------------

    @ApiOperation("获取用户列表")
    @PostMapping("getUserInfoList")
    public WebApiResult<List<UserInfoDto>> getUserInfoList(@RequestBody PageRequestParam param) {
        log.info("获取用户列表（分页查询），接收到请求参数:{}", JSONUtil.toJsonStr(param));
        try {
            PageInfo<SysUser> pageInfo = userService.getUserInfoPage(param);
            List<SysUser> infoList = pageInfo.getList();
            List<UserInfoDto> userInfoDtos = userInfoConvert.userDoToDto(infoList);
            return WebApiResult.ok(userInfoDtos,pageInfo.getSize());
        } catch (Exception e) {
            log.error("获取用户列表（分页查询），异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
    }


    // ----------------关联操作-------------




}
