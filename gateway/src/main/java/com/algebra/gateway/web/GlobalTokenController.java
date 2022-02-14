package com.algebra.gateway.web;

import com.algebra.gateway.entity.AuthTypeProps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author al
 * @date 2022/1/25 11:20
 * @description
 */
@RestController
@Slf4j
public class GlobalTokenController {

    @Autowired
    AuthTypeProps authTypeProps;

    @GetMapping("/getTempToken")
    public void getTempGlobalUserToken(@RequestParam("ssl") String ssl, @RequestParam("type") String type) {
        log.info("获取临时Token：ssl = {}, type = {}", ssl, type);
        try {
            


        } catch (Exception e) {


        }

    }

}
