package com.architecture.java.business.application.user.service;

import com.architecture.java.business.application.user.ports.input.RemoveUserUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RemoveUserService implements RemoveUserUseCase {

    @Override
    public void removeUser(Integer id) {

    }
}
