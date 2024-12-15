package com.architecture.java.business.application.user.service;

import com.architecture.java.business.application.user.ports.input.RemoveUserUseCase;
import com.architecture.java.business.domain.user.models.User;
import com.architecture.java.business.domain.user.ports.output.UserPersistencePort;
import com.architecture.java.business.domain.user.rules.ValidateExistenceAndGetUserRule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RemoveUserService implements RemoveUserUseCase {

    private final UserPersistencePort userPersistencePort;
    private final ValidateExistenceAndGetUserRule validateExistenceAndGetUserRule;

    @Override
    public void removeUser(Integer id) {

        var user = new User().setId(id);
        validateExistenceAndGetUserRule.apply(user);
        userPersistencePort.remove(user);
    }
}
