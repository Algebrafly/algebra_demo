package com.algebra.authentication.web;

import com.algebra.authentication.domain.UserInfoVo;
import com.algebra.authentication.util.WebApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author al
 * @date 2020/7/2 10:01
 * @description
 */
@RestController
@Api(value = "Test", tags = "测试接口")
public class TestController {

    @PostMapping("/loginTest")
    @ApiOperation("测试鉴权")
    public WebApiResult<UserInfoVo> loginTest(String msg){
        



        return WebApiResult.ok(msg);
    }


}
