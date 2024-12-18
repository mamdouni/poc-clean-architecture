package com.decathlon.domain_name.biz_ctx.infra.secondary.database.user.mappers;

import com.decathlon.domain_name.biz_ctx.domain.models.User;
import com.decathlon.domain_name.biz_ctx.infra.secondary.database.user.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);
}
