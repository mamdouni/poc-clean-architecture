package com.decathlon.domain_name.biz_ctx.application.user.service;

import com.decathlon.domain_name.biz_ctx.application.user.usecase.input.port.CreateUserUseCase;
import com.decathlon.domain_name.biz_ctx.domain.models.User;
import com.decathlon.domain_name.biz_ctx.domain.outputs.port.UserPersistencePort;
import com.decathlon.domain_name.biz_ctx.domain.rules.user.ValidateUserCreationRule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateUserService implements CreateUserUseCase {

    // TODO only the service can call the domain, the domain can't call the service
    private final ValidateUserCreationRule validateUserCreationRule;
    private final UserPersistencePort userPersistencePort;

    @Override
    public Optional<User> createUser(User user) {

        validateUserCreationRule.test(user);
        return userPersistencePort.save(user);
    }
}
