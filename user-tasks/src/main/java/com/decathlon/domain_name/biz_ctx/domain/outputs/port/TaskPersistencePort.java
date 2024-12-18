package com.decathlon.domain_name.biz_ctx.domain.outputs.port;

import com.decathlon.domain_name.biz_ctx.domain.models.Task;

import java.util.Optional;

public interface TaskPersistencePort {

    Optional<Task> findByTitle(String title);

    Optional<Task> findById(Integer id);
}
