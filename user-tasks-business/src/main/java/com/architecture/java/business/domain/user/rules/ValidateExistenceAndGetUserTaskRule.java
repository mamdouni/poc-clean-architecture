package com.architecture.java.business.domain.user.rules;

import com.architecture.java.business.domain.exceptions.ResourceNotFoundException;
import com.architecture.java.business.domain.user.models.Task;
import com.architecture.java.business.domain.user.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

import static com.architecture.java.business.domain.exceptions.ExceptionsMessagesEnum.NOT_FOUND_TASK;


@Service
@AllArgsConstructor
public class ValidateExistenceAndGetUserTaskRule implements BiFunction<User, Task, Task> {

    private ValidateExistenceAndGetUserRule validateExistenceAndGetUserRule;

    @Override
    public Task apply(User user, Task task) {

        var requestedUser = validateExistenceAndGetUserRule
                .apply(user);

        return requestedUser.getTasks()
                .stream()
                .filter(t -> t.getId().equals(task.getId()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_TASK, task.getId()));
    }
}
