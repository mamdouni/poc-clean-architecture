package com.architecture.java.cleanarchi.repositories;

import com.architecture.java.cleanarchi.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
}
