package com.architecture.java.infra.primary.rest.user.mappers;

import com.architecture.java.business.domain.user.models.Task;
import com.architecture.java.infra.primary.rest.user.dtos.TaskDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskRestMapper {

    TaskDTO toDTO(Task task);

    Task toDomain(TaskDTO taskDTO);
}
