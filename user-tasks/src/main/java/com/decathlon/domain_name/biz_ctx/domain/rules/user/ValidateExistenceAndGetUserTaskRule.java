package com.decathlon.domain_name.biz_ctx.domain.rules.user;

import com.decathlon.domain_name.biz_ctx.domain.exceptions.ResourceNotFoundException;
import com.decathlon.domain_name.biz_ctx.domain.models.Task;
import com.decathlon.domain_name.biz_ctx.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

import static com.decathlon.domain_name.biz_ctx.domain.exceptions.ExceptionsMessagesEnum.NOT_FOUND_TASK;


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
