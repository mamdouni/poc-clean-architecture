package com.decathlon.domain_name.biz_ctx.application.user.usecase.input.port;

import com.decathlon.domain_name.biz_ctx.domain.models.Task;
import com.decathlon.domain_name.biz_ctx.domain.models.User;

import java.util.List;

public interface DisplayUsersUseCases {

    List<User> findAll();

    User findById(Integer id);

    List<Task> getUserTasks(Integer userId);

    Task getUserTask(Integer userId, Integer taskId);
}
