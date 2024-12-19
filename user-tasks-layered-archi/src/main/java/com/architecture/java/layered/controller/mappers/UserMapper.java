package com.architecture.java.layered.controller.mappers;

import com.architecture.java.layered.controller.dtos.UserDTO;
import com.architecture.java.layered.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(UserEntity userEntity);

    UserEntity toEntity(UserDTO userDTO);
}
