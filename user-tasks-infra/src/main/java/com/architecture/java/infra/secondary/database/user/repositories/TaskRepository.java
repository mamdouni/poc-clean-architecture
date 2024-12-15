package com.architecture.java.infra.secondary.database.user.repositories;

import com.architecture.java.infra.secondary.database.user.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
}
