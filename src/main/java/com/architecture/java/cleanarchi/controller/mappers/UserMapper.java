package com.architecture.java.cleanarchi.controller.mappers;

import com.architecture.java.cleanarchi.controller.dtos.UserDTO;
import com.architecture.java.cleanarchi.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(UserEntity userEntity);

    UserEntity toEntity(UserDTO userDTO);
}
