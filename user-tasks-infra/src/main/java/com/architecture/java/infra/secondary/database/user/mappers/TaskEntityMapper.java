package com.architecture.java.infra.secondary.database.user.mappers;

import com.architecture.java.business.domain.user.models.Task;
import com.architecture.java.infra.secondary.database.user.entities.TaskEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskEntityMapper {

    TaskEntity toEntity(Task task);

    Task toDomain(TaskEntity taskEntity);
}
