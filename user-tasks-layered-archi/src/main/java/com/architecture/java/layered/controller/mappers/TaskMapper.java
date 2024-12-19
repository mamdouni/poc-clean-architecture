package com.architecture.java.layered.controller.mappers;

import com.architecture.java.layered.controller.dtos.TaskDTO;
import com.architecture.java.layered.entities.TaskEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDTO toDTO(TaskEntity taskEntity);

    TaskEntity toEntity(TaskDTO taskDTO);
}
