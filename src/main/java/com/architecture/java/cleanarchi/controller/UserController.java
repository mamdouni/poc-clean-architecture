package com.architecture.java.cleanarchi.controller;

import com.architecture.java.cleanarchi.controller.dtos.UserDTO;
import com.architecture.java.cleanarchi.controller.mappers.UserMapper;
import com.architecture.java.cleanarchi.exceptions.ExceptionsMessagesEnum;
import com.architecture.java.cleanarchi.exceptions.ResourceNotFoundException;
import com.architecture.java.cleanarchi.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    @ResponseStatus(OK)
    public List<UserDTO> getAllUsers() {

        return userService.findAll().stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public UserDTO getUserById(@PathVariable Long id) {

        return userService.findById(id.intValue())
                .map(userMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionsMessagesEnum.NOT_FOUND_USER));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public UserDTO createUser(@RequestBody UserDTO userDTO) {

        return userMapper.toDTO(
                userService.save(
                        userMapper.toEntity(userDTO)
                )
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable Integer id) {

        userService.deleteById(id);
    }
}
