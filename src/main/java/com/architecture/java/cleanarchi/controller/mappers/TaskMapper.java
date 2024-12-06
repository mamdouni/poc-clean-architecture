package com.architecture.java.cleanarchi.controller.mappers;

import org.mapstruct.Mapper;
import com.architecture.java.cleanarchi.controller.dtos.TaskDTO;
import com.architecture.java.cleanarchi.entities.TaskEntity;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDTO toDTO(TaskEntity taskEntity);

    TaskEntity toEntity(TaskDTO taskDTO);
}
