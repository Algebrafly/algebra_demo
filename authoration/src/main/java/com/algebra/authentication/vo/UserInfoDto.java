package com.algebra.authentication.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author al
 * @date 2020/7/3 10:30
 * @description
 */
@Data
public class UserInfoDto implements Serializable {

    private String usrId;

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

    private List<String> roleIds;

}