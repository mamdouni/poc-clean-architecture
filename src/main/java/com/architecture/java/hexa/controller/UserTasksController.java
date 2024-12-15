package com.architecture.java.hexa.controller;

import com.architecture.java.hexa.controller.dtos.TaskDTO;
import com.architecture.java.hexa.controller.mappers.TaskMapper;
import com.architecture.java.hexa.controller.mappers.UserMapper;
import com.architecture.java.hexa.entities.UserEntity;
import com.architecture.java.hexa.exceptions.ExceptionsMessagesEnum;
import com.architecture.java.hexa.exceptions.ResourceNotFoundException;
import com.architecture.java.hexa.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users/{userId}/tasks")
public class UserTasksController {

    private final UserService userService;
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
    public TaskDTO createTask(@PathVariable Integer userId, @RequestBody TaskDTO taskDTO) {

        return userService.createTask(userId, taskMapper.toEntity(taskDTO))
                .map(taskMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionsMessagesEnum.NOT_FOUND_USER));
    }

    @GetMapping("/{taskId}")
    @ResponseStatus(OK)
    public TaskDTO getUserTask( @PathVariable Long userId, @PathVariable Integer taskId) {

        return getUserEntity(userId)
                .getTasks()
                .stream()
                .filter(task -> task.getId().equals(taskId))
                .findFirst()
                .map(taskMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionsMessagesEnum.NOT_FOUND_TASK));
    }

    private UserEntity getUserEntity(Long userId) {
        return userService.findById(userId.intValue())
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionsMessagesEnum.NOT_FOUND_USER));
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(NO_CONTENT)
    public void removeTask(@PathVariable Integer userId, @PathVariable Integer taskId) {

        userService.removeTask(userId, taskId);
    }
}
