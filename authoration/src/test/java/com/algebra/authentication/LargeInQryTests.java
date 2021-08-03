package com.algebra.authentication;


import cn.hutool.json.JSONUtil;
import com.algebra.authentication.mapper.config.TmTestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LargeInQryTests {

    @Autowired
    TmTestMapper testMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getInParam() {
        List<String> ids = testMapper.getInParam(50000);
        // 1. 格式 VALUES('1'),('2'),('3') ...
        StringBuilder sb = new StringBuilder();
//        String formatStr1 = ids.stream().map(s -> sb.append("('").append(s).append("'),")).collect(Collectors.joining());
        for (String s : ids) {
            sb.append("('").append(s).append("'),");
        }
        String formatStr1 = sb.substring(0, sb.lastIndexOf(","));
//        System.out.println(formatStr1);

        // 2. 格式 {'1','2','3'}
        String pattern = "\\{[^}]*\\}";
        String formatStr2 = JSONUtil.toJsonStr(ids);
        String s = formatStr2.replaceAll("\\[", "\\{")
                .replaceAll("\\]", "\\}").replaceAll("\"","");
        System.out.println(s);

    }


}
