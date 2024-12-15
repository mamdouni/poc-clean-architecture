package com.architecture.java.business.domain.user.rules;

import com.architecture.java.business.domain.ValidatorBusinessRule;
import com.architecture.java.business.domain.exceptions.BadRequestException;
import com.architecture.java.business.domain.exceptions.ExceptionsMessagesEnum;
import com.architecture.java.business.domain.user.models.User;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ValidateUserCreationRule implements ValidatorBusinessRule<User> {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    @Override
    public boolean test(User user) {

        if(Objects.isNull(user))
            throw new BadRequestException(ExceptionsMessagesEnum.EMPTY_REQUEST);

        if (StringUtils.isBlank(user.getUsername()))
            throw new BadRequestException(ExceptionsMessagesEnum.EMPTY_USERNAME);

        if (StringUtils.isBlank(user.getEmail()) || !user.getEmail().matches(EMAIL_REGEX))
            throw new BadRequestException(ExceptionsMessagesEnum.NOT_VAlID_EMAIL);

        return true;
    }
}
