package com.decathlon.domain_name.biz_ctx.domain.rules.task;

import com.decathlon.domain_name.biz_ctx.domain.rules.ValidatorBusinessRule;
import com.decathlon.domain_name.biz_ctx.domain.models.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ValidateTaskTitleEmptinessRule implements ValidatorBusinessRule<Task> {

    @Override
    public boolean test(Task task) {

        return StringUtils.isBlank(task.getTitle());
    }
}
