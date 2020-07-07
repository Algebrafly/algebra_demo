package com.algebra.authentication.util.struct;

import com.algebra.authentication.domain.SysUser;
import com.algebra.authentication.vo.UserInfoDto;
import org.mapstruct.Mapper;

/**
 * @author al
 * @date 2020/7/7 17:24
 * @description
 */
@Mapper(componentModel = "spring")
public interface UserInfoConvert {

    SysUser userDtoToDo(UserInfoDto dto);

}
