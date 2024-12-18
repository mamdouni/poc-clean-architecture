package com.decathlon.domain_name.biz_ctx.domain.rules.task;

import com.decathlon.domain_name.biz_ctx.domain.rules.ValidatorBusinessRule;
import com.decathlon.domain_name.biz_ctx.domain.models.Task;
import com.decathlon.domain_name.biz_ctx.domain.outputs.port.TaskPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ValidateTaskUniquenessRule implements ValidatorBusinessRule<Task> {

    private final TaskPersistencePort taskPersistencePort;

    @Override
    public boolean test(Task task) {

        return taskPersistencePort.findByTitle(task.getTitle()).isEmpty();
    }
}
