package com.decathlon.domain_name.biz_ctx.infra.secondary.database.user.mappers;

import com.decathlon.domain_name.biz_ctx.domain.models.Task;
import com.decathlon.domain_name.biz_ctx.infra.secondary.database.user.entities.TaskEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskEntityMapper {

    TaskEntity toEntity(Task task);

    Task toDomain(TaskEntity taskEntity);
}
