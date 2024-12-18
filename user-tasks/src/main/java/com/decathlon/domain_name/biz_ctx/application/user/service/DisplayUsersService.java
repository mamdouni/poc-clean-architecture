package com.decathlon.domain_name.biz_ctx.application.user.service;

import com.decathlon.domain_name.biz_ctx.application.user.usecase.input.port.DisplayUsersUseCases;
import com.decathlon.domain_name.biz_ctx.domain.models.Task;
import com.decathlon.domain_name.biz_ctx.domain.models.User;
import com.decathlon.domain_name.biz_ctx.domain.outputs.port.UserPersistencePort;
import com.decathlon.domain_name.biz_ctx.domain.rules.user.ValidateExistenceAndGetUserRule;
import com.decathlon.domain_name.biz_ctx.domain.rules.user.ValidateExistenceAndGetUserTaskRule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DisplayUsersService implements DisplayUsersUseCases {

    // TODO you can use a service to handle more than one use case.
    private final ValidateExistenceAndGetUserRule validateExistenceAndGetUserRule;
    private final ValidateExistenceAndGetUserTaskRule validateExistenceAndGetUserTaskRule;
    private final UserPersistencePort userPersistencePort;

    @Override
    public List<User> findAll() {

        return userPersistencePort.findAll();
    }

    @Override
    public User findById(Integer userId) {

        return getUser(userId);
    }

    private User getUser(Integer userId) {
        return validateExistenceAndGetUserRule
                .apply(new User().setId(userId));
    }

    @Override
    public List<Task> getUserTasks(Integer userId) {

        return getUser(userId)
                .getTasks();
    }

    @Override
    public Task getUserTask(Integer userId, Integer taskId) {

        return validateExistenceAndGetUserTaskRule
                .apply(new User().setId(userId), new Task().setId(taskId));
    }
}
