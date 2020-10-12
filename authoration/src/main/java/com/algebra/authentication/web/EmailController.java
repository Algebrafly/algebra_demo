package com.algebra.authentication.web;

import com.algebra.authentication.util.MailUtil;
import com.algebra.authentication.util.WebApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author al
 * @date 2020/10/12 11:33
 * @description
 */
@RestController
@Slf4j
@Api(value = "Email", tags = "邮件服务")
public class EmailController {

    @Autowired
    MailUtil mailUtil;

    @ApiOperation("Email发送测试")
    @PostMapping("/sendEmail")
    public WebApiResult<Void> sendSimpleEmail(@RequestParam("to") String to,
                                              @RequestParam("subject") String subject,
                                              @RequestParam("content") String content) {

        mailUtil.sendSimpleMailMessage(to,subject,content);

        return WebApiResult.ok();
    }


}
