package com.algebra.authentication.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author al
 * @date 2020/7/9 11:15
 * @description
 */
@Data
public class RoleMenuVo implements Serializable {

    private String roleId;

    private List<String> menuIds;
}
