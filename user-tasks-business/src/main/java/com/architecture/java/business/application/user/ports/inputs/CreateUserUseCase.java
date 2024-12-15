package com.architecture.java.business.application.user.ports.inputs;

import com.architecture.java.business.domain.user.models.User;

import java.util.Optional;

public interface CreateUserUseCase {

    Optional<User> createUser(User user);
}
