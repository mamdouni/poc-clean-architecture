package com.decathlon.domain_name.biz_ctx.application.user.usecase.input.port;

import com.decathlon.domain_name.biz_ctx.domain.models.User;

import java.util.Optional;

public interface CreateUserUseCase {

    Optional<User> createUser(User user);
}
