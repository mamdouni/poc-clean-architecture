package com.architecture.java.business.domain.user.rules;

import com.architecture.java.business.domain.ValidatorBusinessRule;
import com.architecture.java.business.domain.user.models.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ValidateTaskTitleEmptinessRule implements ValidatorBusinessRule<Task> {

    @Override
    public boolean test(Task task) {

        return StringUtils.isBlank(task.getTitle());
    }
}
