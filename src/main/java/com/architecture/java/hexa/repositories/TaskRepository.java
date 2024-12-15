package com.architecture.java.hexa.repositories;

import com.architecture.java.hexa.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
}
