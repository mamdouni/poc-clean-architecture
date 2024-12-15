package com.architecture.java.business.application.user.service;

import com.architecture.java.business.application.user.ports.input.DisplayUsersUseCases;
import com.architecture.java.business.domain.user.models.Task;
import com.architecture.java.business.domain.user.models.User;
import com.architecture.java.business.domain.user.ports.output.UserPersistencePort;
import com.architecture.java.business.domain.user.rules.ValidateExistenceAndGetUserRule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DisplayUsersService implements DisplayUsersUseCases {

    private final ValidateExistenceAndGetUserRule validateExistenceAndGetUserRule;
    private final UserPersistencePort userPersistencePort;

    @Override
    public List<User> findAll() {

        return userPersistencePort.findAll();
    }

    @Override
    public User findById(Integer id) {

        return validateExistenceAndGetUserRule
                .apply(new User().setId(id));
    }

    @Override
    public List<Task> getUserTasks(Integer userId) {

        return List.of();
    }

    @Override
    public Task getUserTask(Integer userId, Integer taskId) {

        return null;
    }
}
