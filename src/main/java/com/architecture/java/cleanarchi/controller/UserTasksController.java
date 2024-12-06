package com.architecture.java.cleanarchi.controller;

import com.architecture.java.cleanarchi.controller.dtos.TaskDTO;
import com.architecture.java.cleanarchi.controller.dtos.UserDTO;
import com.architecture.java.cleanarchi.controller.mappers.TaskMapper;
import com.architecture.java.cleanarchi.controller.mappers.UserMapper;
import com.architecture.java.cleanarchi.exceptions.ExceptionsMessagesEnum;
import com.architecture.java.cleanarchi.exceptions.ResourceNotFoundException;
import com.architecture.java.cleanarchi.services.TaskService;
import com.architecture.java.cleanarchi.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping("/users/{userId}/tasks")
public class UserTasksController {

    private final UserService userService;
    private final TaskService taskService;
    private final UserMapper userMapper;
    private final TaskMapper taskMapper;

    @GetMapping
    @ResponseStatus(OK)
    public List<TaskDTO> getUserTasks(@PathVariable Long userId) {

        var user = userService.findById(userId.intValue())
                .map(userMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionsMessagesEnum.NOT_FOUND_USER));
        return user.tasks();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public TaskDTO createTask(@PathVariable Long userId, @RequestBody TaskDTO taskDTO) {

        var user = userService.findById(userId.intValue())
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionsMessagesEnum.NOT_FOUND_USER));
        user.addTask(taskMapper.toEntity(taskDTO));
        user = userService.save(user);
        return user.getTasks().stream()
                .filter(task -> task.getTitle().equals(taskDTO.title()))
                .findFirst()
                .map(taskMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionsMessagesEnum.NOT_FOUND_TASK));
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public TaskDTO getUserTask(@PathVariable Long id, @PathVariable Long userId) {

        return taskService.findById(id.intValue())
                .map(taskMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionsMessagesEnum.NOT_FOUND_TASK));
    }
}
