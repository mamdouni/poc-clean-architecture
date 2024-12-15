package com.architecture.java.business.domain.user.ports.outputs;

import com.architecture.java.business.domain.user.models.Task;

import java.util.Optional;

public interface TaskPersistencePort {

    Optional<Task> findByTitle(String title);

    Optional<Task> findById(Integer id);
}
