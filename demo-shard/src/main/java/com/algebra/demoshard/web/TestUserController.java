package com.algebra.demoshard.web;

import com.algebra.demoshard.entity.domain.TUser;
import com.algebra.demoshard.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author al
 * @date 2020/2/17 16:57
 * @description
 */
@RestController
public class TestUserController {

    @Autowired
    IUserService userService;

    @GetMapping("/saveUsers")
    public void saveUsers(){
        List<TUser> users = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            TUser user = TUser.builder()
                    .id((long)(i+1))
                    .name("test"+i)
                    .cityId(i%2==0?1:2)
                    .createTime(new Date())
                    .sex(i % 2 == 0)
                    .phone("11111111"+1)
                    .email("aaaaaaa@"+i)
                    .password("asdasdasd"+i)
                    .build()
                    ;
            users.add(user);
        }
        userService.saveUsers(users);
    }

}
