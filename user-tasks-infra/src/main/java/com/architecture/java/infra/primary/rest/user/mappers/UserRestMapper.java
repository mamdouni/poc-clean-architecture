package com.architecture.java.infra.primary.rest.user.mappers;

import com.architecture.java.business.domain.user.models.User;
import com.architecture.java.infra.primary.rest.user.dtos.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRestMapper {

    UserDTO toDTO(User user);

    User toDomain(UserDTO userDTO);
}
