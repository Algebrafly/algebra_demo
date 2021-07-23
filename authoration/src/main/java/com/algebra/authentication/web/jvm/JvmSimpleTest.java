package com.algebra.authentication.web.jvm;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.algebra.authentication.domain.SysUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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

    public static void main(String[] args) {

//        Date cuur = new Date();

        DateTime curr = DateUtil.date();
        curr.setField(DateField.HOUR_OF_DAY,0);
        curr.setField(DateField.MINUTE,0);
        curr.setField(DateField.SECOND,0);

        System.out.println(curr.toString());

        DateTime offset = DateUtil.offset(curr, DateField.DAY_OF_MONTH, -7);

        System.out.println(offset.toString());
        System.out.println(offset.toDateStr());

    }

}
