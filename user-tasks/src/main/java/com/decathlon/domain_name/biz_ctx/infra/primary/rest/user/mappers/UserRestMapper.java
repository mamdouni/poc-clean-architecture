package com.decathlon.domain_name.biz_ctx.infra.primary.rest.user.mappers;

import com.decathlon.domain_name.biz_ctx.domain.models.User;
import com.decathlon.domain_name.biz_ctx.infra.primary.rest.user.dtos.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRestMapper {

    UserDTO toDTO(User user);

    User toDomain(UserDTO userDTO);
}
