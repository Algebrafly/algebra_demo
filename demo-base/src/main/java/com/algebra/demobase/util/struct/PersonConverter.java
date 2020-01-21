package com.algebra.demobase.util.struct;

import com.algebra.demo.dto.PersonDto;
import com.algebra.demobase.entity.domain.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author al
 * @date 2020/1/21 9:43
 * @description person-map-struct
 */
@Mapper(componentModel = "spring")
public interface PersonConverter {

    /**
     * person DO->DTO
     * @param person domain
     * @return DTO
     */
    @Mappings({
            @Mapping(source = "personName",target = "name"),
            @Mapping(source = "personAge",target = "personAge"),
            @Mapping(source = "personBirthday",target = "personBirthday", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    PersonDto personDoToDto(Person person);

    /**
     * 列表转换
     */
    List<PersonDto> personDoToDtos(List<Person> persons);

}
