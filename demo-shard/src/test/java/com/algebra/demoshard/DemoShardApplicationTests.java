package com.algebra.demoshard;

import com.algebra.demoshard.entity.domain.TUser;
import com.algebra.demoshard.mapper.TUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoShardApplicationTests {

    @Resource
    TUserMapper userMapper;

    @Test
    public void contextLoads() {
        TUser user = TUser.builder().phone("1111").name("tom").cityId(1).build();
        userMapper.insert(user);
    }

}
