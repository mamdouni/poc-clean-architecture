package com.architecture.java.infra.secondary.database.user.adapters;

import com.architecture.java.business.domain.user.models.User;
import com.architecture.java.business.domain.user.ports.output.UserPersistencePort;
import com.architecture.java.infra.secondary.database.user.mappers.UserEntityMapper;
import com.architecture.java.infra.secondary.database.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserJpaAdapter implements UserPersistencePort {

    private final UserRepository repository;
    private final UserEntityMapper mapper;

    @Override
    public List<User> findAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<User> findByID(Integer id) {

        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public void saveAll(List<User> users) {

        repository.saveAll(
                users.stream()
                        .map(mapper::toEntity)
                        .toList()
        );
    }

    @Override
    public void remove(User user) {

        repository.delete(
                mapper.toEntity(user)
        );
    }

    @Override
    public Optional<User> save(User user) {

        return Optional.of(
                mapper.toDomain(
                        repository.save(
                                mapper.toEntity(user)
                        )
                )
        );
    }
}
