package com.architecture.java.infra.primary.rest.user.controllers;

import com.architecture.java.business.application.user.ports.inputs.CreateUserUseCase;
import com.architecture.java.business.application.user.ports.inputs.DisplayUsersUseCases;
import com.architecture.java.business.application.user.ports.inputs.RemoveUserUseCase;
import com.architecture.java.infra.primary.rest.user.dtos.UserDTO;
import com.architecture.java.infra.primary.rest.user.mappers.UserRestMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final DisplayUsersUseCases displayUsersUseCases;
    private final CreateUserUseCase createUserUseCase;
    private final RemoveUserUseCase removeUserUseCase;
    private final UserRestMapper userRestMapper;

    @GetMapping
    @ResponseStatus(OK)
    public List<UserDTO> getAllUsers() {

        return displayUsersUseCases.findAll().stream()
                .map(userRestMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public UserDTO getUserById(@PathVariable Integer id) {

        return userRestMapper.toDTO(displayUsersUseCases.findById(id));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public UserDTO createUser(@RequestBody UserDTO userDTO) {

        return createUserUseCase.createUser(userRestMapper.toDomain(userDTO))
                .map(userRestMapper::toDTO)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable Integer id) {

        removeUserUseCase.removeUser(id);
    }
}
