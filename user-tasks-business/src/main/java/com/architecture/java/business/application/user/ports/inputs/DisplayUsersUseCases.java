package com.architecture.java.business.application.user.ports.inputs;

import com.architecture.java.business.domain.user.models.Task;
import com.architecture.java.business.domain.user.models.User;

import java.util.List;

public interface DisplayUsersUseCases {

    List<User> findAll();

    User findById(Integer id);

    List<Task> getUserTasks(Integer userId);

    Task getUserTask(Integer userId, Integer taskId);
}
