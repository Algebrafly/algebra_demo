package com.algebra.demo.api;

import com.algebra.demo.dto.PersonDto;
import com.algebra.demo.util.base.RequestPageUtil;

import java.util.List;

/**
 * @author al
 * @date 2020/1/21 9:40
 * @description person 对外服务接口
 */
public interface IPersonService {

    /**
     * 获取用户列表-分页
     * @param pageUtil 分页参数工具
     * @return list
     */
    List<PersonDto> getPersonList(RequestPageUtil pageUtil);

}
