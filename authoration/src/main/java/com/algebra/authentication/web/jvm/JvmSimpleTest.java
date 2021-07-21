package com.algebra.authentication.web.jvm;

import com.algebra.authentication.domain.SysUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author al
 * @date 2021/7/21 9:33
 * @description
 */
@RestController
public class JvmSimpleTest {

    @GetMapping("/testHeap")
    public String testHeap(@RequestParam("name") String name) {
//        while (true) {
//            new SysUser();
//        }
        return "hello "+name;
    }

}
