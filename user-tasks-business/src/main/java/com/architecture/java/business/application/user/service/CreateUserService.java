package com.architecture.java.business.application.user.service;

import com.architecture.java.business.application.user.ports.input.CreateUserUseCase;
import com.architecture.java.business.domain.user.models.User;
import com.architecture.java.business.domain.user.ports.output.UserPersistencePort;
import com.architecture.java.business.domain.user.rules.ValidateUserCreationRule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateUserService implements CreateUserUseCase {

    private final ValidateUserCreationRule validateUserCreationRule;
    private final UserPersistencePort userPersistencePort;

    @Override
    public Optional<User> createUser(User user) {

        validateUserCreationRule.test(user);
        return userPersistencePort.save(user);
    }
}
