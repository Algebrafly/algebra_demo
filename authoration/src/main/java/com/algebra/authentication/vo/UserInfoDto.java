package com.algebra.authentication.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author al
 * @date 2020/7/3 10:30
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto implements Serializable {

    private String username;

    private String password;

    private String realName;

    private String idcardTyp;

    private String idcardNo;

    private String phone;

    private String email;

    private String usrBranch;

    private String usrOrganization;

    private String usrStatus;

    private String usrSuper;

    private Integer specialRoleId;

    private String remark;

}
