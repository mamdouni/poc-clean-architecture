package com.architecture.java.business.domain.user.rules;

import com.architecture.java.business.domain.ValidatorBusinessRule;
import com.architecture.java.business.domain.user.models.Task;
import com.architecture.java.business.domain.user.ports.outputs.TaskPersistencePort;
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
