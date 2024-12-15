package com.architecture.java.infra.secondary.database.user.mappers;

import com.architecture.java.business.domain.user.models.User;
import com.architecture.java.infra.secondary.database.user.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);
}
