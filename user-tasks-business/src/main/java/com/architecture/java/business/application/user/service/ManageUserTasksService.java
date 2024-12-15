package com.architecture.java.business.application.user.service;

import com.architecture.java.business.application.user.ports.input.ManageUserTasksUseCase;
import com.architecture.java.business.domain.user.models.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ManageUserTasksService implements ManageUserTasksUseCase {

    @Override
    public Optional<Task> createTask(Integer userId, Task task) {
        return Optional.empty();
    }

    @Override
    public void removeTask(Integer userId, Integer taskId) {

    }
}
