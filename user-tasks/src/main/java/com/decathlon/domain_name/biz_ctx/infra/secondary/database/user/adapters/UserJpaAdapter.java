package com.decathlon.domain_name.biz_ctx.infra.secondary.database.user.adapters;

import com.decathlon.domain_name.biz_ctx.domain.models.User;
import com.decathlon.domain_name.biz_ctx.domain.outputs.port.UserPersistencePort;
import com.decathlon.domain_name.biz_ctx.infra.secondary.database.user.mappers.TaskEntityMapper;
import com.decathlon.domain_name.biz_ctx.infra.secondary.database.user.mappers.UserEntityMapper;
import com.decathlon.domain_name.biz_ctx.infra.secondary.database.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserJpaAdapter implements UserPersistencePort {

    private final UserRepository repository;
    private final UserEntityMapper userMapper;
    private final TaskEntityMapper taskEntityMapper;

    @Override
    public List<User> findAll() {

        return repository.findAll()
                .stream()
                .map(userMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<User> findByID(Integer id) {

        return repository.findById(id)
                .map(userMapper::toDomain);
    }

    @Override
    public void remove(User user) {

        repository.delete(
                userMapper.toEntity(user)
        );
    }

    @Override
    public Optional<User> save(User user) {

        var userEntity = userMapper.toEntity(user);
        if (CollectionUtils.isNotEmpty(user.getTasks())) {
            var userTasks = user.getTasks()
                    .stream()
                    .map(taskEntityMapper::toEntity)
                    .collect(Collectors.toSet());
            userEntity.setTasks(userTasks);
        }
        return Optional.of(
                userMapper.toDomain(
                        repository.save(
                                userEntity
                        )
                )
        );
    }
}
