package com.architecture.java.business.domain.user.rules;

import com.architecture.java.business.domain.TransformerBusinessRule;
import com.architecture.java.business.domain.exceptions.ResourceNotFoundException;
import com.architecture.java.business.domain.user.models.User;
import com.architecture.java.business.domain.user.ports.outputs.UserPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.architecture.java.business.domain.exceptions.ExceptionsMessagesEnum.NOT_FOUND_USER;


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
