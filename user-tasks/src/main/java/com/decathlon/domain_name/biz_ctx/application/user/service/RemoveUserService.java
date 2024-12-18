package com.decathlon.domain_name.biz_ctx.application.user.service;

import com.decathlon.domain_name.biz_ctx.application.user.usecase.input.port.RemoveUserUseCase;
import com.decathlon.domain_name.biz_ctx.domain.models.User;
import com.decathlon.domain_name.biz_ctx.domain.outputs.port.UserPersistencePort;
import com.decathlon.domain_name.biz_ctx.domain.rules.user.ValidateExistenceAndGetUserRule;
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
