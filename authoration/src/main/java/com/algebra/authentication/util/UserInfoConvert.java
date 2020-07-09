package com.algebra.authentication.util;

import com.algebra.authentication.domain.SysUser;
import com.algebra.authentication.vo.UserInfoDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author al
 * @date 2020/7/7 17:24
 * @description
 */
@Mapper(componentModel = "spring")
public interface UserInfoConvert {

    SysUser userDtoToDo(UserInfoDto userDto);

    List<SysUser> userDtoToDos(List<UserInfoDto> userDtos);

    UserInfoDto userDoToDto(SysUser userDo);

    List<UserInfoDto> userDoToDto(List<SysUser> userDo);

}
