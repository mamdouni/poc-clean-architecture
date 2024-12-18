package com.decathlon.domain_name.biz_ctx.domain.rules.user;

import com.decathlon.domain_name.biz_ctx.domain.rules.TransformerBusinessRule;
import com.decathlon.domain_name.biz_ctx.domain.exceptions.ResourceNotFoundException;
import com.decathlon.domain_name.biz_ctx.domain.models.User;
import com.decathlon.domain_name.biz_ctx.domain.outputs.port.UserPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.decathlon.domain_name.biz_ctx.domain.exceptions.ExceptionsMessagesEnum.NOT_FOUND_USER;


@Service
@AllArgsConstructor
public class ValidateExistenceAndGetUserRule implements TransformerBusinessRule<User> {

    private UserPersistencePort userPersistencePort;

    @Override
    public User apply(User user) {

        return userPersistencePort.findByID(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_USER, user.getId()));
    }
}
