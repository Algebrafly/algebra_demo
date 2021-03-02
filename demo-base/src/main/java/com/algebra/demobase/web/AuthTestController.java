package com.algebra.demobase.web;

import com.algebra.demo.auth.DataFilter;
import com.algebra.demo.util.base.WebApiResult;
import com.algebra.demobase.entity.domain.Employee;
import com.algebra.demobase.service.EmployeeService;
import com.algebra.demobase.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author al
 * @date 2021/3/2 10:38
 * @description
 */
@RestController
@Api(tags = "测试行数据权限")
public class AuthTestController {

    @Autowired
    EmployeeService employeeService;

    @ApiOperation("测试01")
    @GetMapping("/testAuth01")
    @DataFilter(custstoreidAlias = "dept_id")
    public WebApiResult<List<Employee>> testAuth01(@RequestParam("name") String name){
        List<Employee> listByName = employeeService.getListByName(name);
        return WebApiResult.ok(listByName);
    }

    @ApiOperation("测试02")
    @GetMapping("/testAuth02")
    @DataFilter(custstoreidAlias = "dept_id")
    public WebApiResult<List<Employee>> testAuth02(@RequestParam("name") String name){
        List<Employee> listByName = employeeService.getListByName2(name);
        return WebApiResult.ok(listByName);
    }

    @ApiOperation("测试03")
    @GetMapping("/testAuth03")
    @DataFilter(custstoreidAlias = "dept_id",equipmentAlias = "'2'")
    public WebApiResult<Employee> testAuth03(@RequestParam("name") String name){
        Employee ByName = employeeService.getOneByName(name);
        return WebApiResult.ok(ByName);
    }



}
