package com.architecture.java.infra.secondary.database.user.adapters;

import com.architecture.java.business.domain.user.models.Task;
import com.architecture.java.business.domain.user.ports.outputs.TaskPersistencePort;
import com.architecture.java.infra.secondary.database.user.mappers.TaskEntityMapper;
import com.architecture.java.infra.secondary.database.user.repositories.TaskRepository;
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
