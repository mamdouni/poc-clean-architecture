package com.decathlon.domain_name.biz_ctx.infra.secondary.database.user.adapters;

import com.decathlon.domain_name.biz_ctx.domain.models.Task;
import com.decathlon.domain_name.biz_ctx.domain.outputs.port.TaskPersistencePort;
import com.decathlon.domain_name.biz_ctx.infra.secondary.database.user.mappers.TaskEntityMapper;
import com.decathlon.domain_name.biz_ctx.infra.secondary.database.user.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskJpaAdapter implements TaskPersistencePort {

    private final TaskRepository repository;
    private final TaskEntityMapper mapper;

    @Override
    public Optional<Task> findByTitle(String title) {

        return repository.findByTitle(title)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Task> findById(Integer id) {

        return repository.findById(id)
                .map(mapper::toDomain);
    }
}
