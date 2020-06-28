package com.algebra.demobase.web;

import com.algebra.demo.util.base.RequestPageUtil;
import com.algebra.demo.util.base.WebApiResult;
import com.algebra.demobase.entity.domain.Person;
import com.algebra.demobase.service.PersonService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author al
 * @date 2020/1/21 8:58
 * @description 用户登录接口
 */
@RestController
@Api(value = "Login",tags = "登录接口")
@Slf4j
public class LoginController {

    @Autowired
    PersonService personService;

    /**
     * 登陆页面
     */
    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("user/login");
    }


    @GetMapping("/login")
    @ApiOperation(value = "登录")
    public WebApiResult<String> login(@RequestParam("name") String name, @RequestParam("passward") String password){
        log.info("登陆接口，接收到请求参数：name = {}, password = {}", name, password);
        WebApiResult<String> result = new WebApiResult<>();
        try {
            System.out.println("登陆成功！！");

            result.setMessage("登陆成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("登陆接口请求异常，请检查参数设置，exp-msg:{}",e.getMessage());
            result.setSuccess(false);
            result.setMessage("登陆接口请求异常，请检查参数设置，exp-msg:"+e.getMessage());
        }
        return result;
    }

    @PostMapping("/getUserList")
    @ApiOperation(value = "获取用户列表")
    public WebApiResult<List<Person>> getUserList(@RequestBody RequestPageUtil requestPageUtil){
        log.info("登陆接口，接收到请求参数：pageUtil = {}", JSONObject.toJSON(requestPageUtil));
        WebApiResult<List<Person>> result = new WebApiResult<>();
        try {
            PageInfo<Person> pageInfo = personService.getPersonList(requestPageUtil);
            result.setMessage("查询成功！");
            result.setCount(pageInfo.getSize());
            result.setData(pageInfo.getList());

        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取用户列表接口请求异常，请检查参数设置，exp-msg:{}",e.getMessage());
            result.setSuccess(false);
            result.setMessage("获取用户列表接口请求异常，请检查参数设置，exp-msg:"+e.getMessage());
        }
        return result;
    }
}
