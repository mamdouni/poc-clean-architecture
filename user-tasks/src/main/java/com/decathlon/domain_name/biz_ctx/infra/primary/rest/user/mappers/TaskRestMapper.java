package com.decathlon.domain_name.biz_ctx.infra.primary.rest.user.mappers;

import com.decathlon.domain_name.biz_ctx.domain.models.Task;
import com.decathlon.domain_name.biz_ctx.infra.primary.rest.user.dtos.TaskDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskRestMapper {

    TaskDTO toDTO(Task task);

    Task toDomain(TaskDTO taskDTO);
}
