package com.algebra.authentication.web.rbac;

import cn.hutool.json.JSONUtil;
import com.algebra.authentication.domain.SysBranch;
import com.algebra.authentication.domain.SysOrganization;
import com.algebra.authentication.service.rbac.SysBranchService;
import com.algebra.authentication.service.rbac.SysOrganizationService;
import com.algebra.authentication.util.CommonUtil;
import com.algebra.authentication.util.PageRequestParam;
import com.algebra.authentication.util.WebApiResult;
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
 * @date 2020/7/9 10:52
 * @description 组织机构部门
 */
@Slf4j
@RestController
@Api(value = "Organization", tags = "组织机构部门")
public class OrganizationController {

    @Autowired
    SysOrganizationService organizationService;

    @Autowired
    SysBranchService branchService;

    // --------------------机构----------

    @ApiOperation("新增机构")
    @PostMapping("addOrganization")
    public WebApiResult<String> addOrganization(@RequestBody SysOrganization organization) {
        log.info("新增机构，接收到参数：{}", JSONUtil.toJsonStr(organization));
        try {
            organization.setCreateAt(new Date());
            organization.setOrgId(CommonUtil.createPrimaryKey("org"));
            organizationService.save(organization);

        } catch (Exception e) {
            log.error("新增机构异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
        return WebApiResult.ok("新增机构成功！");
    }

    @ApiOperation("修改机构")
    @PostMapping("modifyOrganization")
    public WebApiResult<String> modifyOrganization(@RequestBody SysOrganization organization) {
        log.info("修改机构，接收到参数：{}", JSONUtil.toJsonStr(organization));
        try {
            organizationService.updateById(organization);

        } catch (Exception e) {
            log.error("修改机构异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
        return WebApiResult.ok("修改机构成功！");
    }

    @ApiOperation("删除机构")
    @GetMapping("deleteOrganization")
    public WebApiResult<String> deleteOrganization(@RequestParam String organizationId) {
        log.info("删除机构，接收到参数：{}", organizationId);
        try {
            organizationService.removeById(organizationId);

        } catch (Exception e) {
            log.error("删除机构异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
        return WebApiResult.ok("删除机构成功！");
    }

    @ApiOperation("查询机构详情")
    @GetMapping("getOneOrganization")
    public WebApiResult<SysOrganization> getOneOrganization(@RequestParam String organizationId) {
        log.info("查询机构详情，接收到参数：{}", organizationId);
        try {
            SysOrganization organization = organizationService.getById(organizationId);

            return WebApiResult.ok(organization);
        } catch (Exception e) {
            log.error("查询机构详情异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
    }

    @ApiOperation("查询机构列表(All)")
    @GetMapping("getOrganizationList")
    public WebApiResult<List<SysOrganization>> getOrganizationList() {
        log.info("查询机构列表(All)");
        try {
            return WebApiResult.ok(organizationService.getOrganizationList());
        } catch (Exception e) {
            log.error("查询机构列表(All)异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
    }

    @ApiOperation("查询机构列表(Page)")
    @PostMapping("getOrganizationListPage")
    public WebApiResult<List<SysOrganization>> getOrganizationListPage(@RequestBody PageRequestParam pageRequestParam) {
        log.info("查询机构列表(Page)，接收到参数：{}", JSONUtil.toJsonStr(pageRequestParam));
        try {
            PageInfo<SysOrganization> pageInfo = organizationService.getUserInfoPage(pageRequestParam);
            return WebApiResult.ok(pageInfo.getList(),pageInfo.getSize());
        } catch (Exception e) {
            log.error("查询机构列表(Page)异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
    }


    // --------------------部门----------

    @ApiOperation("新增部门")
    @PostMapping("addBranch")
    public WebApiResult<String> addBranch(@RequestBody SysBranch branch) {
        log.info("新增部门，接收到参数：{}", JSONUtil.toJsonStr(branch));
        try {
            branch.setCreateAt(new Date());
            branch.setOrgId(CommonUtil.createPrimaryKey("org"));
            branchService.save(branch);

        } catch (Exception e) {
            log.error("新增部门异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
        return WebApiResult.ok("新增部门成功！");
    }

    @ApiOperation("修改部门")
    @PostMapping("modifyBranch")
    public WebApiResult<String> modifyBranch(@RequestBody SysBranch branch) {
        log.info("修改部门，接收到参数：{}", JSONUtil.toJsonStr(branch));
        try {
            branchService.updateById(branch);

        } catch (Exception e) {
            log.error("修改部门异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
        return WebApiResult.ok("修改部门成功！");
    }

    @ApiOperation("删除部门")
    @GetMapping("deleteBranch")
    public WebApiResult<String> deleteBranch(@RequestParam String branchId) {
        log.info("删除部门，接收到参数：{}", branchId);
        try {
            branchService.removeById(branchId);

        } catch (Exception e) {
            log.error("删除部门异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
        return WebApiResult.ok("删除部门成功！");
    }

    @ApiOperation("查询部门详情")
    @GetMapping("getOneBranch")
    public WebApiResult<SysBranch> getOneBranch(@RequestParam String branchId) {
        log.info("查询部门详情，接收到参数：{}", branchId);
        try {
            SysBranch branch = branchService.getById(branchId);

            return WebApiResult.ok(branch);
        } catch (Exception e) {
            log.error("查询部门详情异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
    }

    @ApiOperation("查询部门列表")
    @GetMapping("getBranchList")
    public WebApiResult<List<SysBranch>> getBranchList(@RequestParam("orgId") String orgId) {
        log.info("查询部门列表，请求参数：{}", orgId);
        try {
            return WebApiResult.ok(branchService.getBranchList(orgId));
        } catch (Exception e) {
            log.error("查询部门列表(All)异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
    }

    @ApiOperation("查询部门列表(Page)")
    @PostMapping("getBranchListPage")
    public WebApiResult<List<SysBranch>> getBranchListPage(@RequestBody PageRequestParam pageRequestParam) {
        log.info("查询部门列表(Page)，接收到参数：{}", JSONUtil.toJsonStr(pageRequestParam));
        try {
            PageInfo<SysBranch> pageInfo = branchService.getBranchListPage(pageRequestParam);
            return WebApiResult.ok(pageInfo.getList(),pageInfo.getSize());
        } catch (Exception e) {
            log.error("查询部门列表(Page)异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
    }


}
