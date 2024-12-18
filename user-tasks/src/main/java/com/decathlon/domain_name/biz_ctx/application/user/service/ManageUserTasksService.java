package com.decathlon.domain_name.biz_ctx.application.user.service;

import com.decathlon.domain_name.biz_ctx.application.user.usecase.input.port.ManageUserTasksUseCase;
import com.decathlon.domain_name.biz_ctx.domain.exceptions.BadRequestException;
import com.decathlon.domain_name.biz_ctx.domain.exceptions.ResourceNotFoundException;
import com.decathlon.domain_name.biz_ctx.domain.models.Task;
import com.decathlon.domain_name.biz_ctx.domain.models.User;
import com.decathlon.domain_name.biz_ctx.domain.outputs.port.TaskPersistencePort;
import com.decathlon.domain_name.biz_ctx.domain.outputs.port.UserPersistencePort;
import com.decathlon.domain_name.biz_ctx.domain.rules.user.ValidateExistenceAndGetUserRule;
import com.decathlon.domain_name.biz_ctx.domain.rules.task.ValidateTaskTitleEmptinessRule;
import com.decathlon.domain_name.biz_ctx.domain.rules.task.ValidateTaskUniquenessRule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.decathlon.domain_name.biz_ctx.domain.exceptions.ExceptionsMessagesEnum.NOT_FOUND_TASK;
import static com.decathlon.domain_name.biz_ctx.domain.exceptions.ExceptionsMessagesEnum.NOT_VAlID_TASK;

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

        // TODO you can chain the rules like here
        if(validateTaskTitleEmptinessRule
                .or(validateTaskUniquenessRule.negate())
                .test(task)) {

            // TODO the use case can handle some business rules also
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
