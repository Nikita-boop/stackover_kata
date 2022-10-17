package com.javamentor.qa.platform.webapp.converters;

import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.dto.UserRegistrationDto;
import io.swagger.v3.oas.annotations.media.Schema;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
@Schema(description = "преобразование UserDTO в UserRegistrationDto и обратно")
public abstract class UserConverter {

    @Mapping(target = "fullName", expression = "java(userRegistrationDto.getFirstName() + \" \" + userRegistrationDto.getLastName())")
    public abstract UserDto userRegistrationDtoToUser(UserRegistrationDto userRegistrationDto);

    @InheritInverseConfiguration
    public abstract UserRegistrationDto userToUserRegistrationDto(UserDto userDto);

}
