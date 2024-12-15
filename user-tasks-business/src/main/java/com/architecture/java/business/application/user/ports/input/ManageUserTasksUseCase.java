package com.architecture.java.business.application.user.ports.input;

import com.architecture.java.business.domain.user.models.Task;

import java.util.Optional;

public interface ManageUserTasksUseCase {

    Optional<Task> createTask(Integer userId, Task task);

    void removeTask(Integer userId, Integer taskId);
}
