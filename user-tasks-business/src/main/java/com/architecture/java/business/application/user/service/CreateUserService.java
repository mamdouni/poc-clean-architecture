package com.architecture.java.business.application.user.service;

import com.architecture.java.business.application.user.ports.input.CreateUserUseCase;
import com.architecture.java.business.domain.user.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateUserService implements CreateUserUseCase {

    @Override
    public Optional<User> createUser(User user) {
        return Optional.empty();
    }
}
