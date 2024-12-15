package com.architecture.java.business.application.user.service;

import com.architecture.java.business.application.user.ports.input.ManageUserTasksUseCase;
import com.architecture.java.business.domain.exceptions.BadRequestException;
import com.architecture.java.business.domain.exceptions.ResourceNotFoundException;
import com.architecture.java.business.domain.user.models.Task;
import com.architecture.java.business.domain.user.models.User;
import com.architecture.java.business.domain.user.ports.output.TaskPersistencePort;
import com.architecture.java.business.domain.user.ports.output.UserPersistencePort;
import com.architecture.java.business.domain.user.rules.ValidateExistenceAndGetUserRule;
import com.architecture.java.business.domain.user.rules.ValidateTaskTitleEmptinessRule;
import com.architecture.java.business.domain.user.rules.ValidateTaskUniquenessRule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.architecture.java.business.domain.exceptions.ExceptionsMessagesEnum.NOT_FOUND_TASK;
import static com.architecture.java.business.domain.exceptions.ExceptionsMessagesEnum.NOT_VAlID_TASK;

@Service
@AllArgsConstructor
public class ManageUserTasksService implements ManageUserTasksUseCase {

    private final ValidateTaskTitleEmptinessRule validateTaskTitleEmptinessRule;
    private final ValidateTaskUniquenessRule validateTaskUniquenessRule;
    private final ValidateExistenceAndGetUserRule validateExistenceAndGetUserRule;
    private final UserPersistencePort userPersistencePort;
    private final TaskPersistencePort taskPersistencePort;

    @Override
    public Optional<Task> createTask(Integer userId, Task task) {

        if(validateTaskTitleEmptinessRule
                .or(validateTaskUniquenessRule.negate())
                .test(task)) {
            throw new BadRequestException(NOT_VAlID_TASK);
        }

        var user = validateExistenceAndGetUserRule
                .apply(new User().setId(userId));
        user.addTask(task);
        var savedUser = userPersistencePort.save(user);

        return savedUser
                .stream()
                .flatMap(u -> u.getTasks().stream())
                .filter(t -> t.getTitle().equals(task.getTitle()))
                .findFirst();
    }

    @Override
    public void removeTask(Integer userId, Integer taskId) {

        var user = new User().setId(userId);
        user = validateExistenceAndGetUserRule.apply(user);

        var task = taskPersistencePort.findById(taskId);
        if (task.isEmpty())
            throw new ResourceNotFoundException(NOT_FOUND_TASK, taskId);

        user.removeTask(task.get());
        userPersistencePort.save(user);
    }
}
