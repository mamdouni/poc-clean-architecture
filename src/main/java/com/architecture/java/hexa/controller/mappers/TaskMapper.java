package com.architecture.java.hexa.controller.mappers;

import com.architecture.java.hexa.controller.dtos.TaskDTO;
import com.architecture.java.hexa.entities.TaskEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDTO toDTO(TaskEntity taskEntity);

    TaskEntity toEntity(TaskDTO taskDTO);
}
