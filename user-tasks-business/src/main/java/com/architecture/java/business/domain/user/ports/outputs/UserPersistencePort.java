package com.architecture.java.business.domain.user.ports.outputs;

import com.architecture.java.business.domain.user.models.User;

import java.util.List;
import java.util.Optional;

public interface UserPersistencePort {

    List<User> findAll();

    Optional<User> findByID(Integer id);

    void remove(User user);

    Optional<User> save(User user);
}
