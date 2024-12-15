package com.architecture.java.hexa.controller.mappers;

import com.architecture.java.hexa.controller.dtos.UserDTO;
import com.architecture.java.hexa.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(UserEntity userEntity);

    UserEntity toEntity(UserDTO userDTO);
}
