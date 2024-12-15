package com.architecture.java.business.application.user.service;

import com.architecture.java.business.application.user.ports.inputs.DisplayUsersUseCases;
import com.architecture.java.business.domain.user.models.Task;
import com.architecture.java.business.domain.user.models.User;
import com.architecture.java.business.domain.user.ports.outputs.UserPersistencePort;
import com.architecture.java.business.domain.user.rules.ValidateExistenceAndGetUserRule;
import com.architecture.java.business.domain.user.rules.ValidateExistenceAndGetUserTaskRule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DisplayUsersService implements DisplayUsersUseCases {

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
