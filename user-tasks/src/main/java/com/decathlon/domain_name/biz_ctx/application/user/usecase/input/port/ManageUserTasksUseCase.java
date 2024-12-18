package com.decathlon.domain_name.biz_ctx.application.user.usecase.input.port;

import com.decathlon.domain_name.biz_ctx.domain.models.Task;

import java.util.Optional;

public interface ManageUserTasksUseCase {

    Optional<Task> createTask(Integer userId, Task task);

    void removeTask(Integer userId, Integer taskId);
}
