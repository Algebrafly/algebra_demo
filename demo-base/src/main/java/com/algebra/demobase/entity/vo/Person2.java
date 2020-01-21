package com.algebra.demobase.entity.vo;

import com.algebra.demobase.entity.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author al
 * @date 2019/8/22 16:48
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person2 {

    private Long id;
    private String name;
    private String email;
    private Date birthday;
    private User user;

}
